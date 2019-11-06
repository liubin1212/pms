package com.ujiuye.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParseToString {


    public static Map<String, String> parseParameterMapToMybatisMap(Map<String, Object> parameterMap) {
        Map<String, String> map = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (key.contains("like")){
                key = key.substring(key.indexOf("_")+1);
                value = "%" + value + "%";
            }
            map.put(key,value);
        }
        return map;
    }

    public static String parseParameterToString(Map<String, Object> parameterMap) {
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        String str = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String  value = (String) entry.getValue();
            str = str + "&" + "search_" + key +"=" +value;
        }
        return str;
    }
}
