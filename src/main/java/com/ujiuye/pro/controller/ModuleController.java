package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @RequestMapping(value = "/addModule",method = RequestMethod.POST)
    public String addModule(Module module){
        moduleService.insertSelective(module);
        return "redirect:/module/findAllModule";
    }

    @RequestMapping(value = "/findJsonModuleByAnaId",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> findJsonModuleByAnaId(Integer analysisFk){
        System.out.println("module模块里。。。。"+analysisFk);
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(analysisFk);
        return moduleService.selectByExample(example);
    }
    @RequestMapping(value = "/findAllModule",method = RequestMethod.GET)
    public ModelAndView findAllModule(){
        ModelAndView mv = new ModelAndView("project-model");
        List<Module> list = moduleService.selectByExample(new ModuleExample());
        mv.addObject("list",list);
        return mv;
    }
}
