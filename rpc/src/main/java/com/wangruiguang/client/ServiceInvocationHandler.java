package com.wangruiguang.client;

import com.wangruiguang.client.channel.ClientChannel;
import com.wangruiguang.client.global.GlobalManageService;
import com.wangruiguang.client.loadbalance.LoadBalancer;
import com.wangruiguang.client.loadbalance.LoadBalancerFactory;
import com.wangruiguang.client.util.Assert;
import com.wangruiguang.common.enity.RpcRequest;
import com.wangruiguang.common.exception.RpcErrorCode;
import com.wangruiguang.common.exception.RpcException;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServiceInvocationHandler implements InvocationHandler {

    private Stub stub;

    private LoadBalancer loadBalancer;

    public ServiceInvocationHandler(Stub stub) {
        this.stub = stub;
        this.loadBalancer = LoadBalancerFactory.create(stub.getLoadBalanceType());
        GlobalManageService.initServerInfo(stub);
    }

    public Stub getStub() {
        return stub;
    }

    public void setStub(Stub stub) {
        this.stub = stub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.RpcRequestBuilder.newBuilder()
                .setRequestId(generateRequestId())
                .setInterfaceName(stub.getInterfaceName())
                .setTimeout(stub.getTimeout())
                .setSerialType(stub.getSerialType())
                .setMethod(method)
                .setArgs(args).build();

        String uniqServiceName = stub.getInterfaceName() + "_" + stub.getNode();
        List<ServerInfo> providerList = GlobalManageService.getProvidersByServiceName(uniqServiceName);
        Assert.assertNotEmpty(providerList, new RpcException(RpcErrorCode.NOT_PROVIDER_FOUND));

        ServerInfo serverInfo = loadBalancer.loadBalance(providerList);
        ClientChannel clientChannel = GlobalManageService.getChannel(serverInfo);
        Assert.assertNotNull(clientChannel, new RpcException(RpcErrorCode.NO_ACTIVE_CHANNEL));

        Channel channel = clientChannel.getChannel();
        channel.writeAndFlush(request);

        RequestFuture requestFuture = GlobalManageService.createRequestFuture(String.valueOf(request.getRequestId()));
        if (requestFuture.await(stub.getTimeout(), TimeUnit.MILLISECONDS)) {
            if (requestFuture.isSuccess()) {
                return requestFuture.getResult();
            } else {
                throw (Exception) requestFuture.getError();
            }
        } else {
            throw new RpcException(RpcErrorCode.TIME_OUT);
        }
    }

    private long generateRequestId() {
        return System.currentTimeMillis();
    }
}
