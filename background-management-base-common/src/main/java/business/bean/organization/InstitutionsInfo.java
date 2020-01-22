package business.bean.organization;

import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: InstitutionsInfo
 * @Description: 机构的实体
 * @Author: ZhangJun
 * @Date: 2019/10/13 17:47
 */
@Data
public class InstitutionsInfo extends BaseBean{
    /**
     * 机构名
     */
    protected String institutionsName;
    /**
     * 经营范围
     */
    protected String businessScope;
    /**
     * 机构地址
     */
    protected String institutionsAddress;
    /**
     * 机构状态
     */
    protected String institutionsStatus;
    /**
     * 机构类型
     */
    protected String institutionsType;
    /**
     * 描述
     */
    protected String description;
    /**
     * 负责人
     */
    protected String leaderId;
}
