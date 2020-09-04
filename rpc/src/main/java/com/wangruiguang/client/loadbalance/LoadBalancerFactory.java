package com.wangruiguang.client.loadbalance;

import static com.wangruiguang.client.loadbalance.LoadBalancerType.fromType;

public class LoadBalancerFactory {

    public static LoadBalancer create(int loadBalanceType) {
        LoadBalancerType loadBalancerType = fromType(loadBalanceType);
        switch (loadBalancerType) {

            case ROBIN:
                return new RobinLoadBalancer();
            case RANDOM:
            default:
                return new RandomLoadBalancer();
        }
    }

}
