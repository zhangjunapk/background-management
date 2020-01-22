package business.bean;

import lombok.Data;

/**
 * @ProjectName: background-management
 * @ClassName: Pagination
 * @Description: 分页
 * @Author: ZhangJun
 * @Date: 2019/10/13 18:15
 */
@Data
public class Pagination {
    /**
     * 第几页
     */
    private Integer page;
    /**
     * 一页多少行
     */
    private Integer rows;
    /**
     * 一共有多少条记录
     */
    private Integer records;
    /**
     * 一共有多少页
     */
    private Integer total;
}
