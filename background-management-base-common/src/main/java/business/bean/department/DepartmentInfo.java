package business.bean.department;

import business.bean.organization.BaseBean;
import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: DepartmentInfo
 * @Description: 部门信息
 * @Author: ZhangJun
 * @Date: 2019/10/14 11:43
 */
@Data
public class DepartmentInfo extends BaseBean {
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 部门简称
     */
    private String departmentSimpleName;
    /**
     * 部门类型
     */
    private String departmentType;
    /**
     * 负责人
     */
    private String leaderId;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 分机号
     */
    private String extension;
    /**
     * 部门状态
     */
    private String departmentStatus;
    /**
     * 备注
     */
    private String description;
    /**
     * 层数
     */
    private Integer layer;
}
