package business.bean.organization;

import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: BaseUser
 * @Description: 用户实体
 * @Author: ZhangJun
 * @Date: 2019/10/13 16:41
 */
@Data
public class BaseUser extends BaseBean{
    private String institutionsId;
    private String departmentId;
    private String contactCode;
    private String addressId;
    /**
     * 角色组id
     */
    private String roleGroupId;
}
