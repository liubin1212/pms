package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.PositionExample;
import com.ujiuye.sys.service.PositonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pos")
public class PositionController {
    @Autowired
    PositonService positonService;

    //查旬所有的职位返回json数据
    @RequestMapping(value = "/findJsonPosition" , method = RequestMethod.GET)
    @ResponseBody
    public List<Position> findJsonPosition(){
        List<Position> positions = positonService.selectByExample(new PositionExample());
        return positions;
    }
}
