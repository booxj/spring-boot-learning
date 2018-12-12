package com.springboot.security.mapper;

import com.springboot.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    SysUser selectByName(@Param("username") String username);

}
