package com.springboot.mapper;


import com.springboot.domain.User;

public interface UserMapper {

    /**
     *
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据uid获取用户信息
     * @param uid
     * @return
     */
    User getUserByUid(Integer uid);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertSelective(User user);

}
