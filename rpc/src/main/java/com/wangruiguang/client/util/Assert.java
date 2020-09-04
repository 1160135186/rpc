package com.wangruiguang.client.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

public class Assert {

    public static void assertNotEmpty(Collection collection,RuntimeException e) {
        if (CollectionUtils.isEmpty(collection)) {
            throw e;
        }
    }

    public static void assertNotNull(Object obj,RuntimeException e) {
        if (obj == null) {
            throw e;
        }
    }
}
