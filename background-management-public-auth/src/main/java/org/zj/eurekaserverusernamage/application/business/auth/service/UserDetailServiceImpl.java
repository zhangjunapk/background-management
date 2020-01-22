package org.zj.eurekaserverusernamage.application.business.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @ProjectName: background-management
 * @ClassName: UserDetailServiceImpl
 * @Description: 用于获得用户的信息
 * @Author: ZhangJun
 * @Date: 2019/10/21 17:22
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HashSet<GrantedAuthority> objects = new HashSet<>();
        objects.add(new SimpleGrantedAuthority("query-role"));
        return new User("admin","admin",objects);
    }
}
