package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import com.ujiuye.pro.mapper.ModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleServiceImp implements ModuleService {
    @Resource
    ModuleMapper moduleMapper;

    @Resource
    AnalysisMapper analysisMapper;

    @Override
    public int countByExample(ModuleExample example) {
        return moduleMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ModuleExample example) {
        return moduleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return moduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Module record) {
        return moduleMapper.insert(record);
    }

    @Override
    public int insertSelective(Module record) {
        return moduleMapper.insertSelective(record);
    }

    @Override
    public List<Module> selectByExample(ModuleExample example) {
        List<Module> modules = moduleMapper.selectByExample(example);
        for (Module module : modules) {
            Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
            module.setAnalysis(analysis);
        }
        return modules;
    }

    @Override
    public Module selectByPrimaryKey(Integer id) {
        Module module = moduleMapper.selectByPrimaryKey(id);
        Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
        module.setAnalysis(analysis);
        return module;
    }

    @Override
    public int updateByExampleSelective(Module record, ModuleExample example) {
        return moduleMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Module record, ModuleExample example) {
        return moduleMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Module record) {
        return moduleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Module record) {
        return moduleMapper.updateByPrimaryKey(record);
    }
}
