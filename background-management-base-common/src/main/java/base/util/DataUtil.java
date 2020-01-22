package base.util;

import base.exception.CustomException;
import business.bean.Pagination;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: background-management
 * @ClassName: DataUtil
 * @Description: 数据处理类
 * @Author: ZhangJun
 * @Date: 2019/10/13 17:38
 */
public class DataUtil {
    private static Logger logger= LoggerFactory.getLogger(DataUtil.class.getName());
    /**
     * 把结果转换为对象集合
     * @param maps
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convert(List<Map> maps,Class clazz){
        List<T> result=new ArrayList<>();
        for(Map m:maps){
            result.add((T) convert(m,clazz));
        }
        return result;
    }

    /**
     * 把map转换为对象
     * @param map
     * @param c
     * @return
     */
    public static Object convert(Map<String,Object> map,Class c){
        //把map中的内容转成javaBean
        Object o = null;
        try {
            o = c.newInstance();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                Object fieldValue = entry.getValue();
                ReflectionUtil.setValue(o,StringUtil.UnderlineToHump(entry.getKey()),fieldValue);
            }
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
        return o;
    }
    public static void checkNullCanNull(Object obj, String... canNull) throws CustomException {
        if (obj == null) {
            throw new CustomException("请传递数据" + obj.getClass().getName());
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object o = f.get(obj);
                //如果是空的，并且不再允许空的数组里,那就抛异常
                if (isNull(o) && notInArray(f, canNull)) {
                    throw new CustomException(f.getName() + "不允许为空");
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * 不能为空
     * @param obj
     * @param notNull
     * @throws base.exception.CustomException
     */
    public static void checkNullNotNull(Object obj, String... notNull) throws CustomException {
        if (obj == null) {
            throw new CustomException("请传递数据");
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object o = f.get(obj);
                //如果是空的，
                if (isNull(o) && !notInArray(f, notNull)) {
                    throw new CustomException(f.getName() + "不允许为空");
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
    }
    /**
     * 看你这个字段名是不是在这个数组里面
     *
     * @param field 字段
     * @param array 数组
     * @return 是否不在数组中
     */
    public static boolean notInArray(Field field, String[] array) {
        if (array == null) {
            return true;
        }
        for (String s : array) {
            if (s != null && s.equals(field.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测是否空
     *
     * @param obj 需要检测的对象
     * @return 是否空
     */
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            if ("".equals(obj) || "null".equals(obj) || "undefied".equals(obj)) {
                return true;
            }
        }
        if (obj.getClass() == double.class || obj.getClass() == Double.class) {
            if ((Double) obj == 0.0D) {
                return true;
            }
        }
        if (obj.getClass() == Float.class || obj.getClass() == float.class) {
            if ((Double) obj == 0.0F) {
                return true;
            }
        }
        if (obj.getClass() == Integer.class || obj.getClass() == int.class) {
            if ((Integer) obj == 0) {
                return true;
            }
        }
        if (obj.getClass() == BigDecimal.class ) {
            if (((BigDecimal)obj).compareTo(BigDecimal.ZERO)==0) {
                return true;
            }
        }
        return false;
    }
    /**
     * 检测是否空
     *
     * @param objs
     * @return
     */
    public static boolean isNull(Object... objs) {
        for(Object obj:objs) {
            if(isNull(obj)){
                return true;
            }
        }
        return false;
    }
    /**
     * 通过对象生成update语句
     * @param obj
     * @param tableName
     * @param idFieldName
     * @param dateFormat 日期的格式
     * @return
     */
    public static StringBuilder getUpdateSql(Object obj,String tableName,String dateFormat,String mysqlDateFormat,String[] idFieldName,String [] sysdate){
        StringBuilder sb=new StringBuilder("update "+tableName+" set ");
        SimpleDateFormat simpleDateFormat = null;
        if(dateFormat!=null&&!"".equals(dateFormat)){
            simpleDateFormat=new SimpleDateFormat(dateFormat);
        }
        for(Field f:obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            try {
                Object o = f.get(obj);
                if(o!=null){
                    if(f.getType()== Date.class&&simpleDateFormat!=null){
                        sb.append(StringUtil.HumpToUnderline(f.getName())+"=to_date('"+simpleDateFormat.format(o)+"','"+mysqlDateFormat+"'),");
                    }else {
                        sb.append(StringUtil.HumpToUnderline(f.getName()) + "='" + o + "',");
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        if(sysdate!=null&&sysdate.length>0){
            for(String dateFieldName:sysdate){
                sb.append(StringUtil.HumpToUnderline(dateFieldName)).append("=sysdate,");
            }
        }
        //接下来就是字符串截取，还有拼装条件
        sb.deleteCharAt(sb.length()-1).append(" where 1=1 ");
        for(String fieldName:idFieldName){
            sb.append(" and "+StringUtil.HumpToUnderline(fieldName)+"='"+ReflectionUtil.getFieldValue(obj,fieldName)+"' ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb;
    }
    public static StringBuilder getUpdateSql(Object obj,String tableName,String... idFieldName){
        return getUpdateSql(obj, tableName,"yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd hh24:mi:ss",idFieldName,null);
    }
    public static StringBuilder getUpdateSql(Object obj,String tableName,String dateFormat,String mysqlDateFormat,String... idFieldName){
        return getUpdateSql(obj,tableName,dateFormat,mysqlDateFormat,idFieldName,null);
    }
    public static StringBuilder getUpdateSql(Object obj,String tableName,String[] idFieldName,String[] sysdate){
        return getUpdateSql(obj, tableName,"yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd hh24:mi:ss",idFieldName,sysdate);
    }

    public static StringBuilder getInsertSql(Object obj,String tableName,String fieldDateFormat,String mysqlDateFormat,String[] sysdate,Map<String,String> raw){
        StringBuilder result=new StringBuilder();
        SimpleDateFormat simpleDateFormat = null;
        if(fieldDateFormat!=null&&!"".equals(fieldDateFormat)){
            simpleDateFormat=new SimpleDateFormat(fieldDateFormat);
        }
        StringBuilder preBuilder=new StringBuilder("insert into "+tableName+" (");
        StringBuilder afterBuilder=new StringBuilder(" values (");
        for(Field ff:obj.getClass().getDeclaredFields()){
            ff.setAccessible(true);
            try {
                if(ff.get(obj)==null){
                    //afterBuilder.append("null,");
                    continue;
                }
                preBuilder.append(StringUtil.HumpToUnderline(ff.getName())).append(",");
                if(ff.getType()== Date.class){
                    afterBuilder.append("to_date('"+simpleDateFormat.format(ff.get(obj))+"','"+mysqlDateFormat+"')").append(",");
                }else{
                    afterBuilder.append("'"+ff.get(obj)+"'").append(",");
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        if(sysdate!=null&&sysdate.length>0) {
            for (String sysdateFieldName : sysdate) {
                preBuilder.append(StringUtil.HumpToUnderline(sysdateFieldName)).append(",");
                afterBuilder.append("sysdate,");
            }
        }
        //原生的
        if(raw!=null&&raw.size()>0) {
            for (Map.Entry<String,String> entry:raw.entrySet()) {
                preBuilder.append(entry.getKey()).append(",");
                afterBuilder.append(entry.getValue()).append(",");
            }
        }
        preBuilder.deleteCharAt(preBuilder.length()-1);
        preBuilder.append(")");
        afterBuilder.deleteCharAt(afterBuilder.length()-1);
        afterBuilder.append(")");
        result.append(preBuilder);
        result.append(afterBuilder);
        return result;
    }
    /**
     * 解析object,生成insertSql
     * @param obj
     * @param tableName
     * @return
     */
    public static StringBuilder getInsertSql(Object obj,String tableName,String fieldDateFormat,String mysqlDateFormat){
        return getInsertSql(obj,tableName,fieldDateFormat,mysqlDateFormat,null,null);
    }
    public static StringBuilder getInsertSql(Object obj,String tableName){
        return getInsertSql(obj,tableName,"yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd hh24:mi:ss");
    }
    public static StringBuilder getInsertSql(Object obj,String tableName,String... sysdate){
        return getInsertSql(obj,tableName,"yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd hh24:mi:ss",sysdate,null);
    }
    public static StringBuilder getInsertSql(Object obj,String tableName,Map<String,String> raw){
        return getInsertSql(obj,tableName,"yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd hh24:mi:ss",null,raw);
    }

    private static void inflateCondition(StringBuilder sql, Map<String, Map<String, String>> paramMap) {
        if (paramMap != null) {
            for (Map.Entry<String, Map<String, String>> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                for (Map.Entry<String, String> e : entry.getValue().entrySet()) {
                    if (e.getValue() == null || "".equals(e.getValue()) || "null".equals(e.getValue())) {
                        continue;
                    }
                    if (key != null && !"".equals(key)) {
                        if ("like".equals(key)) {
                            sql.append(" and ").append(e.getKey()).append(" " + key + " ").append("'%").append(e.getValue()).append("%'  ");
                        } else {
                            sql.append(" and ").append(e.getKey()).append(" " + key + " ").append("'").append(e.getValue()).append("'  ");
                        }
                    }

                }
            }
        }
    }
    private static void inflateOrderBy(StringBuilder sql, Map<String, String> orderBy) {
        if (orderBy == null || sql == null) {
            return;
        }
        sql.append(" order by ");
        for (Map.Entry<String, String> entry : orderBy.entrySet()) {
            sql.append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
        }
        sql.deleteCharAt(sql.length()-1);
    }
    /**
     * 填充日期条件
     * @param sql
     * @param dateMap
     * @param dateFormat
     */
    private static void inflateDateCondition(StringBuilder sql, Map<String, Map<String, String>> dateMap, Map<String, String> dateFormat) {
        for(Map.Entry<String,Map<String,String>> entry:dateMap.entrySet()){
            String fieldName = entry.getKey();
            for(Map.Entry<String,String> e:entry.getValue().entrySet()){
                String minDate = e.getKey();
                String maxDate = e.getValue();
                String dateFormatString = dateFormat.get(fieldName);
                //接下来就是拼接sql
                if(minDate!=null&&!"".equals(minDate)){
                    sql.append(" and ").append(fieldName).append(">=to_date('").append(minDate).append("','").append(dateFormatString).append("') ");
                }
                if(maxDate!=null&&!"".equals(maxDate)){
                    sql.append(" and ").append(fieldName).append("<=to_date('").append(maxDate).append("','").append(dateFormatString).append("') ");
                }
            }
        }
        sql.deleteCharAt(sql.length()-1);
    }

    /**
     *返回list结果
     * @param sql sql语句
     * @param paramMap 参数键值对
     * @param dateMap 数据键值对
     * @param orderBy 排序
     * @param dateFormat 日期格式
     * @param pagination 分页
     * @param jdbcTemplate sql执行器
     * @param clazz 要封装的类
     * @param <T> 类泛型
     * @return 返回查询结果
     */
    public static <T>List<T> getListResult(StringBuilder sql, Map<String, Map<String, String>> paramMap, Map<String,Map<String,String>> dateMap, Map<String, String> orderBy, Map<String, String> dateFormat, Pagination pagination, JdbcTemplate jdbcTemplate,Class<T> clazz) {
        if(Objects.isNull(jdbcTemplate)){
            return null;
        }
        if (Objects.isNull(pagination)) {
            pagination=new Pagination();
            pagination.setPage(1);
            pagination.setRows(Integer.MAX_VALUE);
        }
        inflateConditions(sql,paramMap,dateMap,dateFormat,orderBy);
        List<T> listResult = getListResult(sql, pagination, jdbcTemplate, clazz);
        return listResult;
    }

    /**
     * 填充条件到sql
     * @param sql
     * @param paramMap
     * @param dateMap
     * @param dateFormat
     * @param orderBy
     */
    public static void inflateConditions(StringBuilder sql,Map<String, Map<String, String>>paramMap, Map<String,Map<String,String>> dateMap,Map<String, String> dateFormat, Map<String, String> orderBy){
        inflateCondition(sql, paramMap);
        inflateDateCondition(sql,dateMap, dateFormat);
        inflateOrderBy(sql, orderBy);
    }

    /**
     * 通过sql获得查询结果
     * @param sql
     * @param pagination
     * @param jdbcTemplate
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>List<T> getListResult(StringBuilder sql,Pagination pagination,JdbcTemplate jdbcTemplate,Class<T> clazz){
        PageHelper.startPage(pagination.getPage(), pagination.getRows());
        List<T> ts = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(clazz));
        PageInfo pageInfo = new PageInfo(ts);
        pagination.setTotal( pageInfo.getPages());
        pagination.setRecords((int) pageInfo.getTotal());
        pagination.setPage(pageInfo.getPageNum());
        return pageInfo.getList();
    }
}
