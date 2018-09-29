package com.springboot.dao.impl;


import com.mongodb.client.result.UpdateResult;
import com.springboot.dao.UserDao;
import com.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("username").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public List<User> findByPage(int pageNo, int pageSize) {
        Query query = new Query();
        int skip = (pageNo - 1) * pageSize;
        query.skip(skip);// 从那条记录开始
        query.limit(pageSize);// 取多少条记录
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("username", user.getUsername()).set("password", user.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);

    }

    @Override
    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}
