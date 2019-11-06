package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import com.ujiuye.pro.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/func")
public class FunctionController {
    @Autowired
    FunctionService functionService;

    @RequestMapping(value = "/addFunction",method = RequestMethod.POST)
    public String addFunction(Function function){
        int i = functionService.insertSelective(function);
        if (i > 0){
            return "redirect:/func/findAllFunction";
        }
        return "error";
    }

    @RequestMapping(value = "/findAllFunction",method = RequestMethod.GET)
    public ModelAndView findAllFunction(){
        ModelAndView mv = new ModelAndView("project-function");
        List<Function> list = functionService.selectByExample(new FunctionExample());
        mv.addObject("list",list);
        return mv;
    }
}
