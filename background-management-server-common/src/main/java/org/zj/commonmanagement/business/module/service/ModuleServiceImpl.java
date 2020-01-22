package org.zj.commonmanagement.business.module.service;

import business.bean.Pagination;
import business.bean.module.ModuleInfo;
import business.bean.organization.BaseUser;
import business.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: ModuleServiceImpl
 * @Description: 模块服务实现类
 * @Author: ZhangJun
 * @Date: 2019/10/14 21:03
 */
@Service("ModuleServiceImpl")
public class ModuleServiceImpl extends AbstractService implements IModuleService {
    @Override
    public void addOrModify(ModuleInfo moduleInfo) {

    }

    @Override
    public List<ModuleInfo> getModules() {
        BaseUser loginUser = getLoginUser();
        String token = getToken();
        //token拿到
        //我需要根据token去redis中获得他的所有角色
        return null;
    }

    @Override
    public List<ModuleInfo> getModules(Pagination pagination, ModuleInfo moduleInfo) {
        return null;
    }

    @Override
    public ModuleInfo getDetail(ModuleInfo moduleInfo) {
        return null;
    }
}
