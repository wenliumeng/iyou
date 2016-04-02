package cn.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class MeBaseUtils {
    protected static final Log log = LogFactory.getLog(MeBaseUtils.class);

    public static void main(String[] args) {
        String[] ss = new String[]{"s","d","e"};
        System.out.println(splitJoint(ss,"|"));
    }

    /**
     * 将不同类型的数组用指定分隔符分隔后组成字符串
     * @param obj
     * @param separator
     * @return
     */
    public static String splitJoint(Object[] obj,String separator){
        StringBuffer sb = new StringBuffer(obj.length*3);
        int offset = obj.length -1;
        for(int i =0;i < offset;i++){
            sb.append(obj[i]).append(separator);
        }
        sb.append(obj[offset]);
        return sb.toString();
    }

//    public static Object[] getArray(Object[] obj,int from,int end){
//
//    }
}
