package business.bean.module;

import business.bean.organization.BaseBean;
import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: ModuleInfo
 * @Description: 模块信息
 * @Author: ZhangJun
 * @Date: 2019/10/13 16:59
 */
@Data
public class ModuleInfo extends BaseBean {
    private String parentId;
    private String moduleName;
    private String icon;
    private String url;
    private String target;
    private String containMenu;
    private String description;
    private String status;
}
