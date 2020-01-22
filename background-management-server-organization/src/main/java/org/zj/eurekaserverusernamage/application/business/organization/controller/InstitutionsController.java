package org.zj.eurekaserverusernamage.application.business.organization.controller;

import base.exception.CustomException;
import business.bean.Pagination;
import business.bean.organization.InstitutionsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zj.eurekaserverusernamage.application.business.organization.service.IInstitutionsService;

/**
 * @ProjectName: background-management
 * @ClassName: InstitutionsController
 * @Description: 机构管理
 * @Author: ZhangJun
 * @Date: 2019/10/13 19:00
 */
@RestController
@RequestMapping("/Institutions")
public class InstitutionsController {

    @Autowired
    @Qualifier("InstitutionsServiceImpl")
    IInstitutionsService service;

    /**
     * 获得所有机构
     * @param pagination
     * @param institutionsInfo
     */
    @GetMapping("/getList")
    public void getList(Pagination pagination,InstitutionsInfo institutionsInfo){
        try {
            service.getList(pagination,institutionsInfo);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    /**
     * 机构添加或者修改
     * @param institutionsInfo
     */
    @GetMapping("/addOrModify")
    public void addOrModify(InstitutionsInfo institutionsInfo){
        try {
            service.addOrModify(institutionsInfo);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
