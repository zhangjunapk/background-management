package org.zj.eurekaserverusernamage.application.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ProjectName: background-management
 * @ClassName: CustomWebSecurityConfigAdapter
 * @Description: 自定义的用来获得AuthManager
 * @Author: ZhangJun
 * @Date: 2019/10/22 10:15
 */
@Configuration
public class CustomWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthProvider authProvider;

    @Bean
    AuthenticationManager getAuthenticationManager(){
        try {
            return super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 这个方法设置用户名和密码的校验器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/token");
    }
}
