package com.ujiuye.compare.mapper;

import com.ujiuye.compare.bean.Benchmarking;
import com.ujiuye.compare.bean.BenchmarkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BenchmarkingMapper {
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