package base.util;

/**
 * @Auther: ZhangJun
 * @Date: 2019/5/14 11:30
 * @Description: 字符串工具
 */
public class StringUtil {
    /***
     * 下划线命名转为驼峰命名
     *
     * @param para
     *        下划线命名的字符串
     */

    public static String UnderlineToHump(String para){
        para=para.toLowerCase();
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }



    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String HumpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 把单引号替换为'||'成对,这个单引号搞死我了
     * @param str
     * @return
     */
    public static StringBuilder getReplacedSingleQuotes(String str){
        return replace(str,"'","'||  '''' ||'");
    }

    /**
     * 把双引号替换为'||'成对,这个双引号搞死我了
     * @param str
     * @return
     */
    public static StringBuilder getReplaceDoubleQuotes(String str){
        return replace(str,"\"","'||  '''' ||'");
    }

    private static StringBuilder replace(String need,String from,String to){
        StringBuilder result=new StringBuilder();
        String[] split = need.split(from);
        if(split==null||split.length<=1){
            return new StringBuilder(need);
        }
        for(int i=0;i<split.length;i++){
            if(i!=split.length-1){
                result.append(split[i]).append(to);
            }else{
                result.append(split[i]);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String str="<style class='fox_global_style'>div.fox_html_content { line-height: 1.5; }div.fox_html_content { font-size: 14pt; font-family: 微软雅黑; color: rgb(0, 0, 0); line-height: 1.5; }</style> <div style='font-size: 16px;'><span style='background-color: rgba(0, 0, 0, 0); font-family: 楷体; line-height: 1.5;'>尊敬的经销商合作伙伴，您好，</span></div> <div style='font-size: 16px;'><span style='background-color: rgba(0, 0, 0, 0); font-family: 楷体; line-height: 1.5;'>   </span></div> <div style='font-size: 16px;'><span style='background-color: rgba(0, 0, 0, 0); font-family: 楷体; line-height: 1.5;'>   </span> <span style='background-color: rgba(0, 0, 0, 0); font-family: 楷体; line-height: 1.5;'>这封是申请号：（#APPLICATIONNO#）的产品责任险附件，请查收！</span> </div> <br> <div style='font-size: 16px;'><span style='background-color: rgba(0, 0, 0, 0); font-family: 楷体; line-height: 1.5;'>【中金睿丽融资租赁】</span></div>";
        System.out.println(getReplacedSingleQuotes(str));
    }
}
