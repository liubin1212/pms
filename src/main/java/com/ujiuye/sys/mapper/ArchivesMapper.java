package com.ujiuye.sys.mapper;

import com.ujiuye.sys.bean.Archives;
import com.ujiuye.sys.bean.ArchivesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArchivesMapper {
    long countByExample(ArchivesExample example);

    int deleteByExample(ArchivesExample example);

    int deleteByPrimaryKey(String dnum);

    int insert(Archives record);

    int insertSelective(Archives record);

    List<Archives> selectByExample(ArchivesExample example);

    Archives selectByPrimaryKey(String dnum);

    int updateByExampleSelective(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByExample(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);
}