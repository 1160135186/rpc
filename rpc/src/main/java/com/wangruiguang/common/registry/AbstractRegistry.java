package com.wangruiguang.common.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractRegistry implements Registry {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void subscribe(String serviceName, String path, DataChangeListener listener) {
        // 主动触发一次
        refreshData(serviceName,path,listener);

        //监控数据的变化
        scheduledRefreshData(serviceName,path,listener);
    }


    protected void refreshData(String serviceName, String path, DataChangeListener listener) {
        List<String> providerList = getData(serviceName, path,listener);
        listener.onChange(this,serviceName, path, providerList);
    }

    abstract protected void scheduledRefreshData(String serviceName, String path,DataChangeListener listener);

    abstract protected List<String> getData(String serviceName, String path,DataChangeListener listener);

}
