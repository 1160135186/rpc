package com.wangruiguang.common.registry;

import com.google.common.base.Splitter;
import com.wangruiguang.client.ServerInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalRegistry extends AbstractRegistry {

    private String base;

    public LocalRegistry(String base) {
        this.base = base;
    }

    @Override
    protected void scheduledRefreshData(final String serviceName, final String path, final DataChangeListener listener) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                refreshData(serviceName, path, listener);
            }
        }, 3, 3, TimeUnit.SECONDS);
    }

    @Override
    protected List<String> getData(String serviceName, String path, DataChangeListener listener) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(base + path));
            return Splitter.on(",").splitToList(properties.getProperty(serviceName));
        } catch (Exception e) {
            logger.error("get data failed.service={}|path={}", serviceName, path);
        }

        return null;
    }

    @Override
    public void publish(String serviceName, String path, ServerInfo serverInfo) {
        try {

            File file = new File(base + path);
            if (!file.exists()) {
                file.createNewFile();
            }

            Properties properties = new Properties();
            properties.load(new FileInputStream(base + path));
            properties.setProperty(serviceName, serverInfo.createPublishAddress());

            properties.store(new FileOutputStream(base + path), "publish service");
        } catch (Exception e) {
            logger.error("publish error.serverName={}|path={}|server info={}", serviceName, path, serverInfo);
        }
    }
}
