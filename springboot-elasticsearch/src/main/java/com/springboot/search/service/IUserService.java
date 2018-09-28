package com.springboot.search.service;

import com.springboot.search.bean.User;

import java.util.List;

public interface IUserService {

    boolean insert(User user);

    /**
     * 根据关键字搜素
     * @param searchContent
     * @return
     */
    List<User> search(String searchContent);

    /**
     * 根据关键字搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent);

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<User> searchUserByWeight(String searchContent);
}
