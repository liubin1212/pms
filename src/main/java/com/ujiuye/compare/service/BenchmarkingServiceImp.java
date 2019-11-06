package com.ujiuye.compare.service;

import com.ujiuye.compare.bean.Benchmarking;
import com.ujiuye.compare.bean.BenchmarkingExample;
import com.ujiuye.compare.mapper.BenchmarkingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BenchmarkingServiceImp implements BenchmarkingService {
    @Resource
    private BenchmarkingMapper benchmarkingMapper;

    @Override
    public long countByExample(BenchmarkingExample example) {
        return benchmarkingMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(BenchmarkingExample example) {
        return benchmarkingMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return benchmarkingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Benchmarking record) {
        return benchmarkingMapper.insert(record);
    }

    @Override
    public int insertSelective(Benchmarking record) {
        return benchmarkingMapper.insertSelective(record);
    }

    @Override
    public List<Benchmarking> selectByExample(BenchmarkingExample example) {
        return benchmarkingMapper.selectByExample(example);
    }

    @Override
    public Benchmarking selectByPrimaryKey(Integer id) {
        return benchmarkingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Benchmarking record, BenchmarkingExample example) {
        return benchmarkingMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Benchmarking record, BenchmarkingExample example) {
        return benchmarkingMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Benchmarking record) {
        return benchmarkingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Benchmarking record) {
        return benchmarkingMapper.updateByPrimaryKey(record);
    }
}
