package com.ujiuye.pro.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public int countByExample(ProjectExample example) {
        return projectMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ProjectExample example) {
        return projectMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return projectMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(Project record) {
        return projectMapper.insert(record);
    }

    @Override
    public int insertSelective(Project record) {
        return projectMapper.insertSelective(record);
    }

    @Override
    public List<Project> selectByExample(ProjectExample example) {
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project p:projects) {
            Customer customer = customerService.findCustomerById(p.getComname());
            Employee employee = employeeService.selectByPrimaryKey(p.getEmpFk());
            p.setCustomer(customer);
            p.setEmployee(employee);
        }
        return projects;
    }

    @Override
    public Project selectByPrimaryKey(Integer pid) {
        return projectMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateByExampleSelective(Project record, ProjectExample example) {
        return projectMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Project record, ProjectExample example) {
        return projectMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Project> searchProject(Integer cid, String keyword, String orderby) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        if(cid == 0){
            criteria.andPnameLike("%"+keyword+"%");
            ProjectExample.Criteria criteria1 = example.createCriteria();
            criteria1.andComperLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid == 1){
            criteria.andPnameLike("%"+keyword+"%");
        }else{
            criteria.andComperLike("%"+keyword+"%");
        }
        if("startdate".equals(orderby)){
            example.setOrderByClause("starttime");
        }
        if("stopdate".equals(orderby)){
            example.setOrderByClause("endtime");
        }
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project p:projects) {
            Customer customer = customerService.findCustomerById(p.getComname());
            Employee employee = employeeService.selectByPrimaryKey(p.getEmpFk());
            p.setCustomer(customer);
            p.setEmployee(employee);
        }
        return projects;
    }
}
