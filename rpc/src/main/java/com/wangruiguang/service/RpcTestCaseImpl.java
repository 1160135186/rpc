package com.wangruiguang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RpcTestCaseImpl implements RpcTestCase {

    @Override
    public boolean testReturnPrimitive() {
        return true;
    }

    @Override
    public List<String> testReturnList() {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("user");
        return list;
    }

    @Override
    public Map<String, String> testReturnMap() {
        Map<String, String> map = new HashMap<>();
        map.put("admin", "admin");
        map.put("user", "user");
        return map;
    }
}
