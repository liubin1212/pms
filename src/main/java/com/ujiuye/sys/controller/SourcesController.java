package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.service.SourcesService;
import com.ujiuye.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    SourcesService sourcesService;

    //删除一个Sources
    @RequestMapping(value = "/deleteSources",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity deleteSources(Integer id){
        int i = sourcesService.deleteByPrimaryKey(id);
        if (i > 0){
            return ResultEntity.success();
        }
        return ResultEntity.error();
    }

    //更新一个sources
    @RequestMapping(value = "/updateSources" , method = RequestMethod.PUT)
    public String updateSources(Sources sources){
        int i = sourcesService.updateByPrimaryKeySelective(sources);
        if (i > 0){
            return "redirect:/pm.jsp";
        }
        return "error";
    }

    //查询一个目录
    @RequestMapping(value = "/findOneById/{id}", method = RequestMethod.GET)
    public ModelAndView findOneById(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources sources = sourcesService.selectByPrimaryKey(id);
        mv.addObject("onesource",sources);
        return mv;
    }

    //添加一个新的目录
    @RequestMapping(value = "/addSources",method = RequestMethod.POST)
    public String addSources(Sources sources){
        sourcesService.insertSelective(sources);
        return "redirect:/pm.jsp";
    }
    //查询所有的目录
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> jsonList(){

        List<Sources> sources = sourcesService.findJsonList(0);
        queryChildren(sources.get(0));
        return sources;
    }
    //递归查询所有的权限列表
    private void queryChildren(Sources sources) {
        Integer pid = sources.getId();
        List<Sources> jsonList = sourcesService.findJsonList(pid);
        for (Sources sources1 : jsonList) {
            queryChildren(sources1);
        }
        sources.setChildren(jsonList);
    }


}
