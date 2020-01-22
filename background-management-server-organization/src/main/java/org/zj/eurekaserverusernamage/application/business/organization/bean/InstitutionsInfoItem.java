package org.zj.eurekaserverusernamage.application.business.organization.bean;

import business.bean.organization.InstitutionsInfo;
import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: InstitutionsInfoVo
 * @Description: 机构的列表Item
 * @Author: ZhangJun
 * @Date: 2019/10/14 11:50
 */
@Data
public class InstitutionsInfoItem extends InstitutionsInfo {
    /**
     * 部门类型字典表值
     */
    private String institutionsTypeText;
    /**
     * 负责人字典值
     */
    private String leaderIdText;
}
