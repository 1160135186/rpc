package com.wangruiguang.common.registry;

import com.wangruiguang.client.ServerInfo;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.List;

public class ZkRegistry extends AbstractRegistry {

    private String url;

    private int sessionTimeout;

    private CuratorFramework cf;

    public ZkRegistry(String url, int sessionTimeout) {
        this.url = url;
        this.sessionTimeout = sessionTimeout;
        init();
    }

    private void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(url)
                .sessionTimeoutMs(sessionTimeout)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();
    }

    @Override
    protected void scheduledRefreshData(String serviceName, String path, DataChangeListener listener) {
    }

    @Override
    protected List<String> getData(final String serviceName, final String path, final DataChangeListener listener) {
        try {
            cf.getChildren().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent event) throws Exception {
                    if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                        refreshData(serviceName, path, listener);
                    }
                }
            }).forPath(path);
        } catch (Exception e) {
            logger.error("get data failed.service={}|path={}", serviceName, path, e);
        }

        return null;
    }

    @Override
    public void publish(String serviceName, String path, ServerInfo serverInfo) {

    }
}
