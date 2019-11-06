package com.ujiuye.sys.mapper;

import com.ujiuye.sys.bean.EmpRoleExample;
import com.ujiuye.sys.bean.EmpRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpRoleMapper {
    long countByExample(EmpRoleExample example);

    int deleteByExample(EmpRoleExample example);

    int deleteByPrimaryKey(EmpRole key);

    int insert(EmpRole record);

    int insertSelective(EmpRole record);

    List<EmpRole> selectByExample(EmpRoleExample example);

    int updateByExampleSelective(@Param("record") EmpRole record, @Param("example") EmpRoleExample example);

    int updateByExample(@Param("record") EmpRole record, @Param("example") EmpRoleExample example);
}