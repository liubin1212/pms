package com.ujiuye.cust.controller;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/findJsonById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer findJsonById(@PathVariable(value = "id") Integer id){
        return customerService.findCustomerById(id);
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> findJsonListr(){
        return customerService.findAllCustomer();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Customer> list = customerService.search(cid,keyword,orderby);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return  mv;
    }

    @RequestMapping(value = "/deleteCustomers",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteCustomers(@RequestParam(value = "ids[]") Integer[] ids) {
        boolean b = customerService.deleteCustomers(ids);
        Map<String,Object> map = new HashMap<>();
        if (b){
            map.put("statusCode",200);
            map.put("message","删除成功一半");
        }
        return map;
    }

    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    public String addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/cust/findAllCustomer";
    }

    @RequestMapping(value = "/findAllCustomer",method = RequestMethod.GET)
    public ModelAndView findAllCustomer(){
        List<Customer> list = customerService.findAllCustomer();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return  mv;
    }

    @RequestMapping(value = "/findCustomerById/{id}",method = RequestMethod.GET)
    public ModelAndView findCustomerById(@PathVariable(value = "id") Integer id,String mark){
        ModelAndView mv = new ModelAndView("customer-edit");
        Customer customer = customerService.findCustomerById(id);
        mv.addObject("Customer",customer);
        if ("1".equals(mark)){
            mv.setViewName("customer-look");
            return mv;
        }
        if (customer != null){
            return mv;
        }
        mv.setViewName("error");
        return mv;
    }

    @RequestMapping(value = "/updateCustomerById",method = RequestMethod.PUT)
    public String updateCustomerById(Customer customer){
        boolean b = customerService.updateCustomerById(customer);
        if (b){
            return "redirect:/cust/findAllCustomer";
        }
        return "redirect:/error.jsp";
    }
}
