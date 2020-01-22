package org.zj.eurekaserverusernamage.application.business.auth.dao;

import base.exception.CustomException;
import base.util.DataUtil;
import base.util.SecurityUtil;
import business.bean.module.ModuleInfo;
import business.bean.organization.RoleInfo;
import business.bean.organization.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: AuthSqlProvider
 * @Description: 认证的sql
 * @Author: ZhangJun
 * @Date: 2019/10/13 17:42
 */
@Component
public class AuthDataProvider {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名和密码来获得用户
     * @param userInfo
     * @return
     */
    public UserInfo getUser(UserInfo userInfo) throws CustomException {
        DataUtil.checkNullNotNull(userInfo,"accountId","accountPassword");
        String sql="select * from user_info where account_id='"+userInfo.getAccountId()+"' and account_password='"+ SecurityUtil.encryptionPassword(userInfo.getAccountPassword())+"'";
        UserInfo userInfo1 = jdbcTemplate.queryForObject(sql, UserInfo.class);
        if(!DataUtil.isNull(userInfo1)){
            //获得这个用户的角色列表
            return userInfo1;
        }
        return null;
    }

    /**
     * 获取用户的所有角色
     * @param userInfo
     * @return
     */
    public List<RoleInfo> getRoles(UserInfo userInfo) throws CustomException {
        DataUtil.checkNullNotNull(userInfo,"accountId");
        String sql="select * from role_info where id in(select * from base_user where user_id='"+userInfo.getAccountId()+"')";
        List<RoleInfo> roleInfos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RoleInfo.class));
        return roleInfos;
    }

    public static void main(String[] args) {
        String zhangjun249 = SecurityUtil.encryptionPassword("zhangjun249");
        System.out.println(zhangjun249);
    }

    /**
     * 获得所有角色
     * @return
     */
    public List<RoleInfo> getRoles(){
        String sql="select * from role_info ";
        List<RoleInfo> roleInfos = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RoleInfo.class));
        return roleInfos;
    }

    /**
     * 根据角色获得模块列表
     * @param roleInfo
     * @return
     */
    public List<ModuleInfo> getModules(RoleInfo roleInfo) throws CustomException {
        DataUtil.checkNullNotNull(roleInfo,"id");
        String sql="select * from module_info where id in(select module_id from role_module where role_id='"+roleInfo.getId()+"')";
        List<ModuleInfo> moduleInfos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ModuleInfo.class));
        return moduleInfos;
    }
}
