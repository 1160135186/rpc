package com.wangruiguang.client.global;

import com.google.common.collect.Lists;
import com.wangruiguang.client.RequestFuture;
import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.client.Stub;
import com.wangruiguang.client.channel.ClientChannel;
import com.wangruiguang.client.channel.ClientNettyChannelFactory;
import com.wangruiguang.common.registry.DataChangeListener;
import com.wangruiguang.common.registry.Registry;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalManageService {

    /**
     * 服务-服务提供者 映射
     */
    private static Map<String, List<ServerInfo>> serviceProviderMap = new ConcurrentHashMap<>();

    /**
     * 服务提供者-对应的channel 映射
     */
    private static Map<ServerInfo, ClientChannel> serverChannelMap = new ConcurrentHashMap<>();

    /**
     * 请求列表
     */
    private static Map<String, RequestFuture> requestFutureMap = new ConcurrentHashMap<>();

    public static RequestFuture createRequestFuture(String requestId) {
        RequestFuture requestFuture = new RequestFuture();
        requestFutureMap.put(requestId, requestFuture);
        return requestFuture;
    }


    public static List<ServerInfo> getProvidersByServiceName(String uniqServiceName) {
        List<ServerInfo> serverInfoList = serviceProviderMap.get(uniqServiceName);
        return CollectionUtils.isEmpty(serverInfoList) ? Lists.<ServerInfo>newArrayList() : serverInfoList;
    }


    public static ClientChannel getChannel(ServerInfo serverInfo) {
        return serverChannelMap.get(serverInfo);
    }

    public static void initServerInfo(Stub stub) {
        String serviceName = stub.getInterfaceName();
        String node = stub.getNode();
        Registry registry = stub.getRegistry();
        DataChangeListener dataChangeListener = stub.getDataChangeListener();

        registry.subscribe(serviceName, node, dataChangeListener);
    }

    public static void fireUpdateServerInfo(String uniqServiceName, ServerInfo serverInfo) {
        // 更新服务列表
        List<ServerInfo> serverInfoList = serviceProviderMap.get(uniqServiceName);
        if (serverInfoList == null) {
            serverInfoList = new ArrayList<>();
            serviceProviderMap.put(uniqServiceName,serverInfoList);
        }
        serverInfoList.add(serverInfo);


        // 更新channel
        Channel channel = ClientNettyChannelFactory.createChannel(serverInfo);
        ClientChannel clientChannel = new ClientChannel(channel);
        serverChannelMap.put(serverInfo, clientChannel);
    }

    public static void fireDeleteServerInfo(String uniqServiceName, ServerInfo serverInfo) {
        // 从服务列表中移除
        List<ServerInfo> serverInfoList = serviceProviderMap.get(uniqServiceName);
        serverInfoList.remove(serverInfo);
    }

    public static RequestFuture getRequestFuture(String requestId) {
        return requestFutureMap.get(requestId);
    }
}
