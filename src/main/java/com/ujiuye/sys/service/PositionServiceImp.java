package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.PositionExample;
import com.ujiuye.sys.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImp implements PositonService {
    @Resource
    PositionMapper positionMapper;

    @Override
    public long countByExample(PositionExample example) {
        return positionMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PositionExample example) {
        return positionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Position record) {
        return positionMapper.insert(record);
    }

    @Override
    public int insertSelective(Position record) {
        return positionMapper.insertSelective(record);
    }

    @Override
    public List<Position> selectByExample(PositionExample example) {
        return positionMapper.selectByExample(example);
    }

    @Override
    public Position selectByPrimaryKey(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Position record, PositionExample example) {
        return positionMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Position record, PositionExample example) {
        return positionMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Position record) {
        return positionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Position record) {
        return positionMapper.updateByPrimaryKey(record);
    }
}
