package com.springboot.security.customized.mapper;


import com.springboot.security.customized.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    SysUser findByUserName(String username);
}
