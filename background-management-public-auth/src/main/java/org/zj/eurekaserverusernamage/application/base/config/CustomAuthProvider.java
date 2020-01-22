package org.zj.eurekaserverusernamage.application.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.zj.eurekaserverusernamage.application.business.auth.service.UserDetailServiceImpl;

/**
 * @ProjectName: background-management
 * @ClassName: CustomAuthProvider
 * @Description: 验证用户
 * @Author: ZhangJun
 * @Date: 2019/10/22 14:07
 */
@Configuration
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailServiceImpl service;

    /**
     * 这里是检测用户和密码的
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = service.loadUserByUsername(authentication.getName());
        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
