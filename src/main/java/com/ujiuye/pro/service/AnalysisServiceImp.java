package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalysisServiceImp implements AnalysisService {
    @Resource
    private AnalysisMapper analysisMapper;

    @Override
    public int countByExample(AnalysisExample example) {
        return analysisMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AnalysisExample example) {
        return analysisMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return analysisMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Analysis record) {
        return analysisMapper.insert(record);
    }

    @Override
    public int insertSelective(Analysis record) {
        return analysisMapper.insertSelective(record);
    }

    @Override
    public List<Analysis> selectByExample(AnalysisExample example) {
        return analysisMapper.selectByExample(example);
    }

    @Override
    public Analysis selectByPrimaryKey(Integer id) {
        return analysisMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Analysis record, AnalysisExample example) {
        return analysisMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Analysis record, AnalysisExample example) {
        return analysisMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Analysis record) {
        return analysisMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Analysis record) {
        return analysisMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Analysis> searchAnalysis(Integer id, String keyword, String orderby) {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        if (id == 0){
            criteria.andPronameLike("%"+ keyword +"%");
            AnalysisExample.Criteria criteria1 = example.createCriteria();
            criteria1.andTitleLike("%"+ keyword +"%");
            example.or(criteria1);
        }else if (id == 1){
            criteria.andPronameLike("%"+ keyword +"%");
        }else {
            criteria.andTitleLike("%"+ keyword +"%");
        }
        if ("startdate".equals(orderby)){
            example.setOrderByClause("addtime");
        }else if ("updatedate".equals(orderby)){
            example.setOrderByClause("updatetime");
        }
        List<Analysis> analyses = analysisMapper.selectByExample(example);
        return analyses;
    }
}
