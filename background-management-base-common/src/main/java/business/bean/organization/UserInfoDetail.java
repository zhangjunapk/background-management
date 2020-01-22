package business.bean.organization;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: UserInfoDetail
 * @Description: 用户详细信息
 * @Author: ZhangJun
 * @Date: 2019/10/13 16:51
 */
@Data
public class UserInfoDetail {
    /**
     * 以后带这个就能访问了
     */
    private String token;
    /**
     * 用户信息
     */
    private BaseUser baseUser;
    /**
     * 你的所有角色
     */
    private List<RoleInfo> roleInfos;

}
