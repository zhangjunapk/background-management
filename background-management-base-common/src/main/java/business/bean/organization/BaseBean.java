package business.bean.organization;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: background-management
 * @ClassName: BaseBean
 * @Description: 基本的实体bean
 * @Author: ZhangJun
 * @Date: 2019/10/13 16:45
 */
@Data
public class BaseBean implements Serializable {
    protected Integer id;
    protected Date createTime;
    protected String createUser;
    protected Date modifyTime;
    protected String modifyUser;
}
