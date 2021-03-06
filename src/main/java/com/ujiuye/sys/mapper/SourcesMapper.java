package com.ujiuye.sys.mapper;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SourcesMapper {
    long countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);

    List<Sources> findAllSources(@Param("pid") int i, @Param("eid") Integer eid);
}