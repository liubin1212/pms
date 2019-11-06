package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.bean.ForumpostExample;
import com.ujiuye.usual.mapper.ForumpostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ForumposServiceImp implements ForumposService {
    @Resource
    ForumpostMapper forumpostMapper;

    @Override
    public long countByExample(ForumpostExample example) {
        return forumpostMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ForumpostExample example) {
        return forumpostMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer forumid) {
        return forumpostMapper.deleteByPrimaryKey(forumid);
    }

    @Override
    public int insert(Forumpost record) {
        return forumpostMapper.insert(record);
    }

    @Override
    public int insertSelective(Forumpost record) {
        return forumpostMapper.insertSelective(record);
    }

    @Override
    public List<Forumpost> selectByExample(ForumpostExample example) {
        return forumpostMapper.selectByExample(example);
    }

    @Override
    public Forumpost selectByPrimaryKey(Integer forumid) {
        return forumpostMapper.selectByPrimaryKey(forumid);
    }

    @Override
    public int updateByExampleSelective(Forumpost record, ForumpostExample example) {
        return forumpostMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Forumpost record, ForumpostExample example) {
        return forumpostMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Forumpost record) {
        return forumpostMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Forumpost record) {
        return forumpostMapper.updateByPrimaryKey(record);
    }
}
