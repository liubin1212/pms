package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.RoleSources;
import com.ujiuye.sys.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourcesServiceImp implements RoleSourcesService {
    @Resource
    RoleSourcesMapper roleSourcesMapper;

    @Override
    public int insert(RoleSources roleSources) {
        return roleSourcesMapper.insert(roleSources);
    }
}
