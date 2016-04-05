package cn.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iyou.common.exception.BusException;
import org.omg.Dynamic.Parameter;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class MeBaseUtils {
    protected static final Log log = LogFactory.getLog(MeBaseUtils.class);

    public static void main(String[] args) {
        int[] x = int2bytes(99);
        for(int i = 0;i < x.length;i++){
            System.out.println(x[i]);
        }
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

    /**
     * 截取任意元素的数组
     * @param obj
     * @param from
     * @param end
     * @return
     */
    public static Object[] getArray(Object[] obj,int from,int end){
        if(end > obj.length -1){
            throw new BusException("{seymour.MeBaseUtils}数组越界！");
        }
        if(from > end){
            throw new BusException("{seymour.MeBaseUtils}截取范围错误！");
        }
        int sum = end - from + 1;
        Object[] newObj = new Object[sum];
        int j = 0;
        for(int i = from; i <= sum; i++){
            newObj[j++] = obj[i];
        }
        return newObj;
    }

    /**
     * 截取任意对象数组再拼装成字符串
     * @param obj
     * @param from
     * @param end
     * @return
     */
    public static String getArraystr(Object[] obj,int from,int end) {
        if (end > obj.length - 1) {
            throw new BusException("{seymour.MeBaseUtils}数组越界！");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = from; i <= end; i++) {
            stringBuffer.append(obj[i].toString());
        }
        return stringBuffer.toString();
    }

    /**
     * 字符数串组转化为
     * @param strings
     * @return
     */
    public static int[] string2ints(String[] strings){
        int[] ints = new int[strings.length];
        for(int i = 0; i < strings.length;i++){
            ints[i] = Integer.valueOf(strings[i]).intValue();
        }
        return ints;
    }

    /**
     * 任意对象转换为int数组
     * @param obj
     * @return
     */
    public static int[] objs2ints(Object[] obj){
        int[] ints = new int[obj.length];
        for(int i = 0; i < obj.length;i++){
            ints[i] = Integer.valueOf(obj[i].toString()).intValue();
        }
        return ints;
    }

    public static int[] int2bytes(int num){
        return new int[]{(byte) (num >> 8 & 0xFF),(byte)(num & 0xFF)};
    }

    public static short bytes2int(int[] bs){
        if(bs.length >= 2){
            return (short)((bs[0] & 0xFF) << 8 | 0xFF & bs[1]);
        }
        throw new BusException("{seymour.MeBaseUtils} bytes length not less 2");
    }


}
