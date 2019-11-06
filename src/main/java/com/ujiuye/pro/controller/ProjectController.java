package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.service.ProjectService;
import com.ujiuye.sys.bean.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/findJsonProjectByPname",method = RequestMethod.GET)
    @ResponseBody
    public Project findJsonProjectByPname(String pname){
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPnameEqualTo(pname);
        List<Project> projects = projectService.selectByExample(example);
        return projects.get(0);
    }

    @RequestMapping(value = "/findJsonProjects",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> findJsonProjects(){
        return projectService.selectByExample(new ProjectExample());
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchProject(Integer cid,String keyword,String orderby){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> projects = projectService.searchProject(cid,keyword,orderby);
        mv.addObject("list",projects);
        return mv;
    }

    @RequestMapping(value = "/deleteProjects",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteProjects(@RequestParam(value = "pids[]") Integer[] pids){
        Map<String,Object> map = new HashMap<>();
        List<Integer> integers = Arrays.asList(pids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(integers);
        int i = projectService.deleteByExample(example);
        if (i > 0){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/updateProject",method = RequestMethod.PUT)
    public String updateProject(Project project){
        projectService.updateByPrimaryKeySelective(project);
        return "redirect:/pro/findAllProject";
    }

    @RequestMapping(value = "/findProjectById/{id}",method = RequestMethod.GET)
    public ModelAndView findProjectById(@PathVariable("id") Integer pid,String mark){
        ModelAndView mv = new ModelAndView("project-base-edit");
        Project project = projectService.selectByPrimaryKey(pid);
        mv.addObject("project",project);
        if ("1".equals(mark)){
            mv.setViewName("project-base-look");
        }
        return mv;
    }

    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    public String addProject(Project project){
        int i = projectService.insertSelective(project);
        if (i > 0){
            return "redirect:/pro/findAllProject";
        }
        return "error";
    }

    @RequestMapping(value = "/findAllProject",method = RequestMethod.GET)
    public ModelAndView findAllProject(){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> projects = projectService.selectByExample(new ProjectExample());
        mv.addObject("list",projects);
        return mv;
    }
}
