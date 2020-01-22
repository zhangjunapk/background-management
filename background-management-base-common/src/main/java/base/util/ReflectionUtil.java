package base.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @Auther: ZhangJun
 * @Date: 2019/5/14 11:40
 * @Description: 反射工具
 */
public class ReflectionUtil {
    /**
     * 获得一个对象里面字段的值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj,String fieldName){
        if(fieldName==null|"".equals(fieldName)){
            System.out.println("字段名为空,无法获得这个对象里面字段的值");
            return null;
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            if(declaredField==null){
                return null;
            }
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void setValue(Object obj,String fieldName,Object value){
        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            if(declaredField==null){
                return;
            }
            declaredField.setAccessible(true);
            //obj=cconvert(declaredField,value);
            if(value instanceof String && declaredField.getType()==Integer.class){
                value=Integer.parseInt((String) value);
            }
            declaredField.set(obj,value);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static Object cconvert(Field declaredField, Object value) {
        if(value instanceof BigDecimal){
            if(declaredField.getType()==Double.class||declaredField.getType()==double.class){
                return ((BigDecimal)value).doubleValue();
            }
        }
        return value;
    }
}
