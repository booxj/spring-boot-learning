package com.springboot.jwt.controller;

import com.springboot.jwt.bean.User;
import com.springboot.jwt.utils.JwtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.jwt.JwtApplication.USER_MAP;

@RestController
public class UserController {

    @RequestMapping("login")
    private Map login(String username, String password) {
        Map<String, Object> result = new HashMap();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.put("msg", "用户名或密码不能为空");
            return result;
        }

        User user = USER_MAP.get(username);
        if (user != null) {
            user.setLastLoginTime(new Date());
            result.put("token", JwtUtil.generateToken(username, user.getLastLoginTime()));
        } else {
            result.put("msg", "用户名或密码错误");
        }
        return result;
    }

    @RequestMapping("/hello")
    public Map login(HttpServletRequest request) {
        String token = request.getParameter("token");
        return JwtUtil.validateToken(token);
    }
}
