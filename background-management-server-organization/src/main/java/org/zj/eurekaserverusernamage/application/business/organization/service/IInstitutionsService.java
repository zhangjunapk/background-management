package org.zj.eurekaserverusernamage.application.business.organization.service;

import base.exception.CustomException;
import business.bean.Pagination;
import business.bean.organization.InstitutionsInfo;
import org.zj.eurekaserverusernamage.application.business.organization.bean.InstitutionsInfoItem;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: IInstitutionsService
 * @Description: 机构管理
 * @Author: ZhangJun
 * @Date: 2019/10/13 18:06
 */
public interface IInstitutionsService {
    /**
     * 添加或者修改机构
     * @param institutionsInfo
     * @throws CustomException
     */
    void addOrModify(InstitutionsInfo institutionsInfo)throws CustomException;

    /**
     *
     * @param pagination 分页
     * @param institutionsInfo 条件
     * @return 返回列表
     * @throws CustomException
     */
    List<InstitutionsInfoItem> getList(Pagination pagination, InstitutionsInfo institutionsInfo) throws CustomException;
}
