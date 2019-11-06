package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.EmpRole;
import com.ujiuye.sys.mapper.EmpRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpRoleServiceImp implements EmpRoleService {
    @Resource
    EmpRoleMapper empRoleMapper;

    @Override
    public void insert(Integer eid, String[] rid) {
        for (String s : rid) {
            EmpRole empRole = new EmpRole();
            empRole.setEid(eid);
            empRole.setRid(Integer.parseInt(s));
            empRoleMapper.insert(empRole);
        }
    }
}
