package base.util;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @ProjectName: background-management
 * @ClassName: SecurityUtil
 * @Description: 安全工具
 * @Author: ZhangJun
 * @Date: 2019/10/14 21:15
 */
public class SecurityUtil {
    private static String method="SHA-256";
    public static byte[] sha256(String str){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance(method);
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            return digest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对字符串进行sha256加密
     * @param str
     * @return
     */
    public static String encryptionSha256(String str){
        byte[] bytes = sha256(str);
        String s = byte2Hex(bytes);
        return s;
    }

    /**
     * 对字符串进行md5加密
     * @param str
     * @return
     */
    public static String encryptionMd5(String str){
        String s = DigestUtils.md5DigestAsHex(str.getBytes());
        return s;
    }

    /**
     * 对字符串进行base64加密
     * @param str
     * @return
     */
    public static String encryptionBase64(String str){
        BASE64Encoder encoder=new BASE64Encoder();
        String encode = encoder.encode(str.getBytes());
        return encode;
    }

    /**
     * 对密码进行加密
     * @param str
     * @return
     */
    public static String encryptionPassword(String str){
        String s = encryptionMd5(str);
        String s1 = encryptionSha256(str);
        String s2 = encryptionBase64(s + s1);
        return s2;
    }

    /**
     * 生成token
     * @return
     */
    public static String generateToken(){
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        String s1 = encryptionMd5(s);
        String s2 = encryptionBase64(s1);
        return s2;
    }
    public static void main(String[] args) {
        String jjaljdlajdf = encryptionPassword("我的家在东北");
        System.out.println(jjaljdlajdf);
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace);
        String s = encryptionMd5(replace);
        String s1 = encryptionBase64(s);
        System.out.println(s1);
        System.out.println(s);
    }
    private static String byte2Hex(byte[] bytes){
        StringBuffer sb=new StringBuffer();
        String temp=null;
        for(int i=0;i<bytes.length;i++){
            temp=Integer.toHexString(bytes[i]&0xFF);
            if(temp.length()==1){
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
