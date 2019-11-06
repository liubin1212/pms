package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public int countByExample(EmployeeExample example) {
        return employeeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(EmployeeExample example) {
        return employeeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer eid) {
        return employeeMapper.deleteByPrimaryKey(eid);
    }

    @Override
    public int insert(Employee record) {
        return employeeMapper.insert(record);
    }

    @Override
    public int insertSelective(Employee record) {
        return employeeMapper.insertSelective(record);
    }

    @Override
    public List<Employee> selectByExample(EmployeeExample example) {
        return employeeMapper.selectByExample(example);
    }

    @Override
    public Employee selectByPrimaryKey(Integer eid) {
        return employeeMapper.selectByPrimaryKey(eid);
    }

    @Override
    public int updateByExampleSelective(Employee record, EmployeeExample example) {
        return employeeMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Employee record, EmployeeExample example) {
        return employeeMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return employeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Employee> findExportIdEmployee(Integer eid) {
        return employeeMapper.findExportIdEmployee(eid);
    }

    @Override
    public Integer addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
        return employee.getEid();
    }
}
