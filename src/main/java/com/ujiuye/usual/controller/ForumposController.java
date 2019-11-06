package com.ujiuye.usual.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.service.ForumposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/forumpost")
public class ForumposController {
    @Autowired
    ForumposService forumposService;

    @RequestMapping(value = "/addForumpost",method = RequestMethod.POST)
    public String addForumpos(Forumpost forumpost, HttpSession session){
        Employee employee = (Employee) session.getAttribute("loginUser");
        forumpost.setEmpFk(employee.getEid());
        forumpost.setCreatetime(new Date());
        forumposService.insert(forumpost);
        return "redirect:/main.jsp";
    }
}
