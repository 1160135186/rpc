package com.wangruiguang.client.loadbalance;

public enum LoadBalancerType {
    /**
     * 随机
     */
    RANDOM(1),

    /**
     * 轮询
     */
    ROBIN(2)
    ;

    int type;

    LoadBalancerType(int type) {
        this.type = type;
    }

    public static LoadBalancerType fromType(int type) {
        for (LoadBalancerType loadBalancerType : LoadBalancerType.values()) {
            if (type == loadBalancerType.getType()) {
                return loadBalancerType;
            }
        }

        return null;
    }

    public int getType() {
        return type;
    }
}
