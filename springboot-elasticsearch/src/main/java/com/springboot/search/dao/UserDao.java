package com.springboot.search.dao;

import com.springboot.search.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<User,Long> {
}
