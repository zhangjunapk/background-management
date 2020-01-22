package org.zj.commonmanagement.business.module.service;

import business.bean.Pagination;
import business.bean.module.ModuleInfo;
import business.bean.organization.BaseUser;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: IModuleService
 * @Description: 模块的服务
 * @Author: ZhangJun
 * @Date: 2019/10/14 20:47
 */
public interface IModuleService {
    /**
     * 对模块进行添加或修改操作
     * @param moduleInfo
     */
    void addOrModify(ModuleInfo moduleInfo);

    /**
     * 获取用户能访问的模块列表
     * @param
     * @return
     */
    List<ModuleInfo> getModules();

    /**
     * 列表查询模块
     * @param pagination
     * @param moduleInfo
     * @return
     */
    List<ModuleInfo> getModules(Pagination pagination,ModuleInfo moduleInfo);

    /**
     * 获得一个模块的详细信息
     * @param moduleInfo
     * @return
     */
    ModuleInfo getDetail(ModuleInfo moduleInfo);
}
