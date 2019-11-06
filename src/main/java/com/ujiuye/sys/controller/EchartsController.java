package com.ujiuye.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echarts")
public class EchartsController {
    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> getData(){
        Map<String,Object> map = new HashMap<>();
        map.put("衬衫",5);
        map.put("羊毛衫",20);
        map.put("雪纺衫",36);
        map.put("裤子",10);
        map.put("高跟鞋",10);
        map.put("袜子",10);
        return map;
    }
}
