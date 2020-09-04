package com.wangruiguang.client.loadbalance;

import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.client.channel.ClientChannel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机负载
 */
public class RandomLoadBalancer implements LoadBalancer {

    private Random random = ThreadLocalRandom.current();

    @Override
    public ServerInfo loadBalance(List<ServerInfo> serverInfoList) {
        return serverInfoList.get(random.nextInt(serverInfoList.size()));
    }
}
