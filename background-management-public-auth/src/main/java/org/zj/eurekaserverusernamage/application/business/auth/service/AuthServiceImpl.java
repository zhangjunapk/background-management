package org.zj.eurekaserverusernamage.application.business.auth.service;

import base.constants.Constants;
import base.exception.CustomException;
import business.bean.organization.BaseUser;
import business.bean.module.ModuleInfo;
import business.bean.organization.RoleInfo;
import business.bean.organization.UserInfoDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.zj.eurekaserverusernamage.application.business.auth.dao.AuthDataProvider;

import java.util.*;

/**
 * @ProjectName: background-management
 * @ClassName: AuthServiceImpl
 * @Description: 鉴权实现类
 * @Author: ZhangJun
 * @Date: 2019/10/13 17:06
 */
@Service("AuthServiceImpl")
public class AuthServiceImpl implements IAuthService{

    private Logger logger= LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthDataProvider provider;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserInfoDetail login(BaseUser baseUser) {
        return null;
    }

    @Override
    public List<ModuleInfo> getModules(List<RoleInfo> roleInfos) {
        return null;
    }

    @Override
    public void inflateModules() throws CustomException {
        /**
         * 保存角色和模块之间的对应关系集合
         */
        List<Map<String,List<ModuleInfo>>> list=new ArrayList<>();
         /**
         * 我需要获得所有角色
         */
        List<RoleInfo> roles = getRoles();
        for(RoleInfo roleInfo:roles){
            //我要把他们放进去
            Map<String,List<ModuleInfo>> map=new HashMap<>();
            List<ModuleInfo> modules = getModules(roleInfo);
            map.put(String.valueOf(roleInfo.getId()),modules);
            list.add(map);
        }
        /**
         * 集合准备好了，接下来就是放进去
         */
        ValueOperations<String,List<Map<String,List<ModuleInfo>>>> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(Constants.Auth.ROLE_MODULE_MAPPING_KEY,list);

        List<Map<String, List<ModuleInfo>>> list1 = valueOperations.get(Constants.Auth.ROLE_MODULE_MAPPING_KEY);
        logger.info(list1.toString());
    }

    @Override
    public List<RoleInfo> getRoles() {
        List<RoleInfo> roles = provider.getRoles();
        return roles;
    }

    @Override
    public List<ModuleInfo> getModules(RoleInfo roleInfo) throws CustomException {
        List<ModuleInfo> modules = provider.getModules(roleInfo);
        return modules;
    }


}
