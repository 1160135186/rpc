package com.wangruiguang.client;

import com.wangruiguang.common.registry.DataChangeListener;
import com.wangruiguang.common.registry.Registry;

public class Stub {

    private String interfaceName;

    private String node;

    private int retry;

    private int timeout;

    private int serialType;

    private int loadBalanceType;

    private Registry registry;

    private DataChangeListener dataChangeListener;

    public Stub() {
    }

    public Stub(String interfaceName, String node, int retry, int timeout, int serialType, int loadBalanceType, Registry registry, DataChangeListener dataChangeListener) {
        this.interfaceName = interfaceName;
        this.node = node;
        this.retry = retry;
        this.timeout = timeout;
        this.serialType = serialType;
        this.loadBalanceType = loadBalanceType;
        this.registry = registry;
        this.dataChangeListener = dataChangeListener;
    }


    public static class StubBuilder {
        private String interfaceName;

        private String node;

        private int retry;

        private int timeout;

        private int serialType;

        private int loadBalanceType;

        private Registry registry;

        private DataChangeListener dataChangeListener;

        public static StubBuilder newBuilder() {
            return new StubBuilder();
        }

        public Stub build() {
            return new Stub(this.interfaceName,this.node,this.retry,this.timeout,this.serialType,this.loadBalanceType,this.registry,this.dataChangeListener);
        }

        public StubBuilder interfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
            return this;
        }

        public StubBuilder node(String node) {
            this.node = node;
            return this;
        }

        public StubBuilder retry(int retry) {
            this.retry = retry;
            return this;
        }

        public StubBuilder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public StubBuilder serialType(int serialType) {
            this.serialType = serialType;
            return this;
        }

        public StubBuilder loadBalanceType(int loadBalanceType) {
            this.loadBalanceType = loadBalanceType;
            return this;
        }

        public StubBuilder registry(Registry registry) {
            this.registry = registry;
            return this;
        }

        public StubBuilder dataChangeListener(DataChangeListener dataChangeListener) {
            this.dataChangeListener = dataChangeListener;
            return this;
        }

    }


    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public int getLoadBalanceType() {
        return loadBalanceType;
    }

    public void setLoadBalanceType(int loadBalanceType) {
        this.loadBalanceType = loadBalanceType;
    }


    public int getSerialType() {
        return serialType;
    }

    public void setSerialType(int serialType) {
        this.serialType = serialType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public DataChangeListener getDataChangeListener() {
        return dataChangeListener;
    }

    public void setDataChangeListener(DataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }
}
