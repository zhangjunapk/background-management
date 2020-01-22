package business.service;

import base.util.DataUtil;
import business.bean.organization.BaseUser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ProjectName: background-management
 * @ClassName: AbstractService
 * @Description: 通用service
 * @Author: ZhangJun
 * @Date: 2019/10/14 9:38
 */
@Component
public abstract class AbstractService {


    /**
     * 获得登陆的用户
     * @return
     */
    public BaseUser getLoginUser(){
        String token = getToken();
        if(DataUtil.isNull(token)){
            return null;
        }
        return new BaseUser();
    }
    public String getToken(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes){
            String token = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader("token");
            return token;
        }
        return null;
    }
}
