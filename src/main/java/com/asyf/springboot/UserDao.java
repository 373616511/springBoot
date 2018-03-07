package com.asyf.springboot;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    //方式一：不用xml的查询方式，不需要mapper.xml 和 mybatis-config.xml
    @Select("select * from user where name = #{name}")
    User findUserByName(@Param("name") String name);

    //方式二：使用xml的查询方式，需要mapper.xml 和 mybatis-config.xml
    User selectByPrimaryKey(String id);
}
