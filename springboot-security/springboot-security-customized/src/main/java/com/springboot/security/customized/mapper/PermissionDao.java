package com.springboot.security.customized.mapper;


import com.springboot.security.customized.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {

    List<SysPermission> findAll();

    List<SysPermission> findByAdminUserId(int userId);
}
