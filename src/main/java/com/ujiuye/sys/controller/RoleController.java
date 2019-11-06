package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.bean.RoleExample;
import com.ujiuye.sys.bean.RoleSources;
import com.ujiuye.sys.service.RoleService;
import com.ujiuye.sys.service.RoleSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleSourcesService roleSourcesService;

    //查询所有的角色返回json数据
    @RequestMapping(value = "/findJsonRole",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> findJsonRole(){
        List<Role> roles = roleService.selectByExample(new RoleExample());
        return roles;
    }

    //添加一个新的Role
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(Role role,String ids){
        //先向数据库添加一条数据，并获取到添加的id
        Integer roleid = roleService.addRole(role);
        //向role_sources表中添加数据
        String[] sids = ids.split(",");
        for (String sid : sids) {
            RoleSources roleSources = new RoleSources(Integer.parseInt(sid),roleid);
            roleSourcesService.insert(roleSources);
        }
        return "redirect:/role.jsp";
    }
}
