package com.ujiuye.utils;

import java.util.HashMap;
import java.util.Map;
/*
该类是响应信息类
即发送ajax请求，作为返回值的
有成功方法和失败方法，以及添加新的响应信息
 */
public class ResultEntity {
    public Map<String,Object> map = new HashMap<>();

    public ResultEntity put(String key,Object value){
        this.map.put(key,value);
        return this;
    }

    public static ResultEntity success(){
        ResultEntity entity = new ResultEntity();
        entity.map.put("statusCode",200);
        entity.map.put("message","响应成功");
        return entity;
    }

    public static ResultEntity error(){
        ResultEntity entity = new ResultEntity();
        entity.map.put("statusCode",500);
        entity.map.put("message","响应失败");
        return entity;
    }
}
