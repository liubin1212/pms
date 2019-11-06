package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.FunctionExample;
import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.mapper.AnalysisMapper;
import com.ujiuye.pro.mapper.FunctionMapper;
import com.ujiuye.pro.mapper.ModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunctionServiceImp implements FunctionService {
    @Resource
    FunctionMapper functionMapper;

    @Resource
    ModuleMapper moduleMapper;

    @Resource
    AnalysisMapper analysisMapper;

    @Override
    public int countByExample(FunctionExample example) {
        return functionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(FunctionExample example) {
        return functionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return functionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Function record) {
        return functionMapper.insert(record);
    }

    @Override
    public int insertSelective(Function record) {
        return functionMapper.insertSelective(record);
    }

    @Override
    public List<Function> selectByExample(FunctionExample example) {
        List<Function> list = functionMapper.selectByExample(example);
        for (Function function : list) {
            Module module = moduleMapper.selectByPrimaryKey(function.getModeleFk());
            function.setModule(module);
            Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
            function.setAnalysis(analysis);
        }
        return list;
    }

    @Override
    public Function selectByPrimaryKey(Integer id) {
        return functionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Function record, FunctionExample example) {
        return functionMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Function record, FunctionExample example) {
        return functionMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Function record) {
        return functionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Function record) {
        return functionMapper.updateByPrimaryKey(record);
    }
}
