package base.exception;

/**
 * @ProjectName: background-management
 * @ClassName: CustomException
 * @Description: 自定义异常
 * @Author: ZhangJun
 * @Date: 2019/10/13 18:08
 */
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
