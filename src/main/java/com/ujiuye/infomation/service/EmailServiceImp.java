package com.ujiuye.infomation.service;

import com.ujiuye.infomation.bean.Email;
import com.ujiuye.infomation.bean.EmailExample;
import com.ujiuye.infomation.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailServiceImp implements EmailService {
    @Resource
    EmailMapper emailMapper;

    @Override
    public long countByExample(EmailExample example) {
        return emailMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(EmailExample example) {
        return emailMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return emailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Email record) {
        return emailMapper.insert(record);
    }

    @Override
    public int insertSelective(Email record) {
        return emailMapper.insertSelective(record);
    }

    @Override
    public List<Email> selectByExample(EmailExample example) {
        return emailMapper.selectByExample(example);
    }

    @Override
    public Email selectByPrimaryKey(Integer id) {
        return emailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Email record, EmailExample example) {
        return emailMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Email record, EmailExample example) {
        return emailMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Email record) {
        return emailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Email record) {
        return emailMapper.updateByPrimaryKey(record);
    }
}
