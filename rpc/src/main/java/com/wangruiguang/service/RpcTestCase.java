package com.wangruiguang.service;

import java.util.List;
import java.util.Map;

public interface RpcTestCase {

    boolean testReturnPrimitive();

    List<String> testReturnList();

    Map<String, String> testReturnMap();
}
