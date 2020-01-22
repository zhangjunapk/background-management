package business.bean.organization;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: background-management
 * @ClassName: UserInfo
 * @Description: 用户信息
 * @Author: ZhangJun
 * @Date: 2019/10/14 21:35
 */
@Data
public class UserInfo extends BaseBean{
    /**
     * 用户名
     */
    private String userName;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 账户id
     */
    private String accountId;
    /**
     * 账户密码
     */
    private String accountPassword;
    /**
     * 性别
     */
    private String sex;
    /**
     * 备注
     */
    private String description;
    /**
     * 状态
     */
    private String status;
}
