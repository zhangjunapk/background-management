package org.zj.eurekaserverusernamage.application.business.organization.service;

import base.exception.CustomException;
import base.util.DataUtil;
import business.bean.Pagination;
import business.bean.organization.InstitutionsInfo;
import business.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.zj.eurekaserverusernamage.application.business.organization.bean.InstitutionsInfoItem;
import org.zj.eurekaserverusernamage.application.business.organization.dao.InstitutionsInfoSqlProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: background-management
 * @ClassName: InstitutionsServiceImpl
 * @Description: 机构管理的实现类
 * @Author: ZhangJun
 * @Date: 2019/10/13 18:18
 */
@Service("InstitutionsServiceImpl")
public class InstitutionsServiceImpl extends AbstractService implements IInstitutionsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HttpServletRequest request;

    @Override
    public void addOrModify(InstitutionsInfo institutionsInfo) throws CustomException {
        DataUtil.checkNullNotNull(institutionsInfo,"institutionsName");
        if(DataUtil.isNull(institutionsInfo.getId())){
            institutionsInfo.setCreateUser(String.valueOf(getLoginUser().getId()));
            //说明你要新增
            StringBuilder institutions_info = DataUtil.getInsertSql(institutionsInfo, "institutions_info");
            System.out.println(institutions_info);
            jdbcTemplate.execute(institutions_info.toString());
        }else{
            institutionsInfo.setModifyUser(String.valueOf(getLoginUser().getId()));
            //说明你要修改
            DataUtil.getUpdateSql(institutionsInfo,"institutions_info",new String[]{"id"},new String[]{"modify_time"});
        }
    }

    @Override
    public List<InstitutionsInfoItem> getList(Pagination pagination, InstitutionsInfo institutionsInfo)throws CustomException {
        if(DataUtil.isNull(pagination)){
            throw new CustomException("请传递分页数据");
        }
        List<InstitutionsInfoItem> listSql = InstitutionsInfoSqlProvider.getList(pagination, institutionsInfo, jdbcTemplate);
        return listSql;
    }
}
