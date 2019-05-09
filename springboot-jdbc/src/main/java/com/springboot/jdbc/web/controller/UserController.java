package com.springboot.jdbc.web.controller;

import com.springboot.jdbc.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserController.java
 * @Description: TODO
 * @Author: wangbo
 * @CreateDate 2019/5/9 13:47
 * @version:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String QUERY_USERS_SQL = "select * from user";
    private static final String QUERY_USER_BY_ID_SQL = "select * from user where id = ?";
    private static final String DELETE_USER_BY_ID_SQL = "delete from user where id = ?";
    private static final String INSERT_USER_BY_ID_SQL = "insert into user(name,age,birthday) values(?,?,?)";
    private static final String UPDATE_USER_BY_ID_SQL = "update user set name=?,age=?,birthday=? where id =?";

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping
    public List<User> queryUsers() {
        return jdbcTemplate.query(QUERY_USERS_SQL, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User queryUserById(@PathVariable int id) {
        return jdbcTemplate.queryForObject(QUERY_USER_BY_ID_SQL, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) {
        return jdbcTemplate.update(DELETE_USER_BY_ID_SQL, id);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public int addUser(@RequestBody User user) {
        return jdbcTemplate.update(INSERT_USER_BY_ID_SQL, user.getName(), user.getAge(), user.getBirthday());
    }

    /**
     * 更新用户
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public int editUser(@PathVariable Long id, @RequestBody User user) {
        return jdbcTemplate.update(UPDATE_USER_BY_ID_SQL, user.getName(), user.getAge(), user.getBirthday(), id);
    }
}
