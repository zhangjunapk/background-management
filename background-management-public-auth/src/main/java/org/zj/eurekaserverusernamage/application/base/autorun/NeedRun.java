package org.zj.eurekaserverusernamage.application.base.autorun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.zj.eurekaserverusernamage.application.business.auth.service.IAuthService;

/**
 * @ProjectName: background-management
 * @ClassName: NeedRun
 * @Description: 需要执行的
 * @Author: ZhangJun
 * @Date: 2019/10/15 21:08
 */
@Component
@Order(1)
public class NeedRun implements CommandLineRunner {
    @Autowired
    IAuthService service;

    private Logger logger= LoggerFactory.getLogger(NeedRun.class);

    @Override
    public void run(String... args) throws Exception {
        //项目启动后，填充角色和模块之间的映射关系
        logger.info("-------------------填充角色和模块之间的映射关系到redis");
        service.inflateModules();
        logger.info("-------------------角色和模块之间的映射关系填充成功");
    }
}
