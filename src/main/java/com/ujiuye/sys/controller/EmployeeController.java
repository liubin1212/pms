package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.EmpRoleService;
import com.ujiuye.sys.service.EmployeeService;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmpRoleService empRoleService;

    @Autowired
    private SourcesService sourcesService;

    //添加一个新员工
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public String addEmployee(Employee employee,String[] rid){
        Integer eid = employeeService.addEmployee(employee);
        empRoleService.insert(eid,rid);
        return "redirect:/user.jsp";
    }

    //查询id和发件人不同的员工
    @RequestMapping(value = "/findExportIdEmployee",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> findExportIdEmployee(HttpSession session){
        Employee employee = (Employee) session.getAttribute("loginUser");
        List<Employee> employees = employeeService.findExportIdEmployee(employee.getEid());
        return employees;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Employee employee, String valicode, HttpSession session, RedirectAttributes redirectAttributes){
        String code = (String) session.getAttribute("validateCode");
        if (code.equalsIgnoreCase(valicode)){
            session.removeAttribute("validateCode");
            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(employee.getUsername());
            criteria.andPasswordEqualTo(employee.getPassword());
            List<Employee> employees = employeeService.selectByExample(example);
            if (employees != null && employees.size() > 0){
                employee = employees.get(0);
                List<Sources> list = sourcesService.findAllSources(employee.getEid());
                session.setAttribute("loginUser",employee);
                session.setAttribute("list",list);
                return "redirect:/index.jsp";
            }else {
                redirectAttributes.addFlashAttribute("msg","用户名或者密码错误");
                return "redirect:/login";
            }
        }
        redirectAttributes.addFlashAttribute("username",employee.getUsername());
        redirectAttributes.addFlashAttribute("password",employee.getPassword());
        redirectAttributes.addFlashAttribute("msg","验证码错误");
        return "redirect:/login";
    }

    @RequestMapping(value = "/findEmployeeById",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManager(){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> list = employeeService.selectByExample(example);
        return list;
    }
}
