package org.zj.eurekaserverusernamage.application.business.auth.service;

import base.exception.CustomException;
import business.bean.organization.BaseUser;
import business.bean.module.ModuleInfo;
import business.bean.organization.RoleInfo;
import business.bean.organization.UserInfo;
import business.bean.organization.UserInfoDetail;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: IAuthService
 * @Description: 认证服务
 * @Author: ZhangJun
 * @Date: 2019/10/13 16:40
 */
public interface IAuthService {


    /**
     * 用户登录
     * @param baseUser 用户登录信息
     * @return 用户详细信息和角色列表
     */
    UserInfoDetail login(BaseUser baseUser);

    /**
     * 根据角色获得模块信息
     * @param roleInfos
     * @return
     */
    List<ModuleInfo> getModules(List<RoleInfo> roleInfos);

    /**
     * 每次登陆的时候都把角色和模块之间的对应关系加载到缓存
     */
    void inflateModules() throws CustomException;

    /**
     * 获得所有角色
     * @return
     */
    List<RoleInfo> getRoles();

    /**
     * 根据角色获得模块列表
     * @param roleInfo
     * @return
     */
    List<ModuleInfo> getModules(RoleInfo roleInfo) throws CustomException;
}
