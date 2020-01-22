package org.zj.eurekaserverusernamage.application.business.organization.dao;

import base.util.DataUtil;
import business.bean.DictConstants;
import business.bean.Pagination;
import business.bean.organization.InstitutionsInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.zj.eurekaserverusernamage.application.business.organization.bean.InstitutionsInfoItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: background-management
 * @ClassName: InstitutionsInfoSqlProvider
 * @Description: 机构的sql
 * @Author: ZhangJun
 * @Date: 2019/10/14 10:03
 */
public class InstitutionsInfoSqlProvider {
    /**
     * 获得机构列表
     * @param pagination
     * @param institutionsInfo
     * @param jdbcTemplate
     * @return
     */
    public static List<InstitutionsInfoItem> getList(Pagination pagination, InstitutionsInfo institutionsInfo, JdbcTemplate jdbcTemplate){
        //这里对条件进行判断
        StringBuilder sb=new StringBuilder("select ii.*,bd.dict_text institutions_type_text,ui.user_name leader_id_text from institutions_info ii left join business_dictionary bd on (bd.dict_type='")
                .append(DictConstants.INSTITUTIONS_TYPE).append("' and platform='").append(DictConstants.PLATFORM_BM).append(" left join base_user bu on bu.id=ii.leader_id left join user_info ui on ui.id=bu.user_id").append("' where 1=1 ");
        Map<String, Map<String,String>> paramMap=new HashMap<>();
        if(!DataUtil.isNull(institutionsInfo)){
            Map<String,String> likeMap=new HashMap<>();
            likeMap.put("ii.institutions_name",institutionsInfo.getInstitutionsName());
            likeMap.put("ii.leader_id",institutionsInfo.getLeaderId());
        }

        List<InstitutionsInfoItem> listResult = DataUtil.getListResult(sb, pagination, jdbcTemplate, InstitutionsInfoItem.class);
        return listResult;
    }
}
