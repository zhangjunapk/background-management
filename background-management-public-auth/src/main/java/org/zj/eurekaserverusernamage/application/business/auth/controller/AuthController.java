package org.zj.eurekaserverusernamage.application.business.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ProjectName: background-management
 * @ClassName: AuthController
 * @Description: 用于认证的controller
 * @Author: ZhangJun
 * @Date: 2019/10/22 10:26
 */
@Controller
public class AuthController {
    @PostMapping("/login")
    public void login(@RequestBody Map<String,String> map){
        System.out.println("-----------");
    }
}
