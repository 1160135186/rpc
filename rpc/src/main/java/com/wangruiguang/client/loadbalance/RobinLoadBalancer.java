package com.wangruiguang.client.loadbalance;

import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.client.channel.ClientChannel;
import io.netty.channel.Channel;

import javax.print.attribute.standard.Severity;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 轮询
 */
public class RobinLoadBalancer implements LoadBalancer {

    private AtomicLong counter = new AtomicLong(0);

    @Override
    public ServerInfo loadBalance(List<ServerInfo> serverInfoList) {
        return serverInfoList.get(counter.intValue() % serverInfoList.size());
    }
}
