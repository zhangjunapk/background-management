/*
package org.zj.eurekaserverusernamage.application.base.onfig;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

*/
/**
 * @ProjectName: background-management
 * @ClassName: ResourceConfig
 * @Description: 资源服务的配置,每个需要认证的资源服务加上这个配置
 * @Author: ZhangJun
 * @Date: 2019/10/21 16:57
 *//*

//@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request,response,authException)->response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }
}
*/
