package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoXiaoService;
import com.ujiuye.utils.ParseToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/baoxiao")
public class BaoXiaoController {
    @Autowired
    BaoXiaoService baoXiaoService;

    @RequestMapping(value = "/findAllBaoxiao",method = RequestMethod.GET)
    public ModelAndView findAllBaoxiao(HttpServletRequest request,HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") String pageNum){
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String requestURI = request.getRequestURI();
        String queryStr = ParseToString.parseParameterToString(parameterMap);
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        PageInfo<Baoxiao> pageInfo = (PageInfo<Baoxiao>) baoXiaoService.findAllBaoxiao(eid,pageNum,parameterMap);
        mv.addObject("page",pageInfo);
        mv.addObject("queryStr",queryStr);
        mv.addObject("requestURI",requestURI);
        return mv;
    }



    @RequestMapping(value = "/addBaoxiao",method = RequestMethod.POST)
    public String addBaoxiao(Baoxiao baoxiao, HttpSession session){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        baoxiao.setEmpFk(loginUser.getEid());
        baoxiao.setBxid(UUID.randomUUID().toString().replaceAll("-",""));
        baoXiaoService.insertSelective(baoxiao);
        return "redirect:/baoxiao/findAllBaoxiao";
    }
}
