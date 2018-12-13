package com.springboot.security.support;

import com.springboot.security.entity.SysPermission;
import com.springboot.security.entity.SysRole;
import com.springboot.security.entity.SysUser;
import com.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (SysRole role : sysUser.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
            for (SysPermission permission : role.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(permission.getUrl()));
            }
        }

        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
