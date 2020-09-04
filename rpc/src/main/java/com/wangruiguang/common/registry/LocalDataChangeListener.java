package com.wangruiguang.common.registry;

import java.util.List;

public class LocalDataChangeListener extends AbstractDataChangeListener {

    @Override
    protected void postProcess(Registry registry, String serviceName, String path, List<String> providerList) {
        logger.info("post process");

    }
}
