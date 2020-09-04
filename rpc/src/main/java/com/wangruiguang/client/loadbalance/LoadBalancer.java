package com.wangruiguang.client.loadbalance;

import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.client.channel.ClientChannel;
import io.netty.channel.Channel;

import java.util.List;

public interface LoadBalancer {

    ServerInfo loadBalance(List<ServerInfo> serverInfoList);
}
