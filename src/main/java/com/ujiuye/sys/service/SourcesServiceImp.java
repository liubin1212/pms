package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImp implements SourcesService {
    @Resource
    SourcesMapper sourcesMapper;

    @Override
    public long countByExample(SourcesExample example) {
        return sourcesMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SourcesExample example) {
        return sourcesMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sources record) {
        return sourcesMapper.insert(record);
    }

    @Override
    public int insertSelective(Sources record) {
        return sourcesMapper.insertSelective(record);
    }

    @Override
    public List<Sources> selectByExample(SourcesExample example) {
        return sourcesMapper.selectByExample(example);
    }

    @Override
    public Sources selectByPrimaryKey(Integer id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Sources record, SourcesExample example) {
        return sourcesMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Sources record, SourcesExample example) {
        return sourcesMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Sources record) {
        return sourcesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Sources record) {
        return sourcesMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Sources> findJsonList(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    @Override
    public List<Sources> findAllSources(Integer eid) {
        List<Sources> list = sourcesMapper.findAllSources(1,eid);
        for (Sources sources : list) {
            List<Sources> list1 = sourcesMapper.findAllSources(sources.getId(),eid);
            sources.setChildren(list1);
        }
        return list;
    }
}
