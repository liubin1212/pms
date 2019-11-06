package com.ujiuye.compare.service;

import com.ujiuye.compare.bean.Benchmarking;
import com.ujiuye.compare.bean.BenchmarkingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BenchmarkingService {
    long countByExample(BenchmarkingExample example);

    int deleteByExample(BenchmarkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Benchmarking record);

    int insertSelective(Benchmarking record);

    List<Benchmarking> selectByExample(BenchmarkingExample example);

    Benchmarking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Benchmarking record, @Param("example") BenchmarkingExample example);

    int updateByExample(@Param("record") Benchmarking record, @Param("example") BenchmarkingExample example);

    int updateByPrimaryKeySelective(Benchmarking record);

    int updateByPrimaryKey(Benchmarking record);
}
