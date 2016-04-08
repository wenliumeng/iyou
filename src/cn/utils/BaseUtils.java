package cn.utils;

import org.apache.commons.lang.ArrayUtils;
import org.iyou.common.exception.BusException;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class BaseUtils {

    /**
     * 将不同类型的数组用指定分隔符分隔后组成字符串
     * @param obj 传入类型
     * @param separator 分隔符
     * @return 字符串
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
     * @param obj 数组元素
     * @param from 截取开始处
     * @param end 截取停止处
     * @return 返回的数组
     */
    public static Object[] getArray(Object[] obj,int from,int end){
        if(end > obj.length -1){
            throw new BusException("{seymour.BaseUtils}数组越界！");
        }
        if(from > end){
            throw new BusException("{seymour.BaseUtils}截取范围错误！");
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
     * @param obj 对象数组
     * @param from 截取开始处
     * @param end 截取停止处
     * @return 返回的字符串
     */
    public static String getArraystr(Object[] obj,int from,int end) {
        if (end > obj.length - 1) {
            throw new BusException("{seymour.BaseUtils}数组越界！");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = from; i <= end; i++) {
            stringBuffer.append(obj[i].toString());
        }
        return stringBuffer.toString();
    }

    /**
     * 字符数串组转化为Int类型数组
     * @param strings 字符串
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

    /**
     * int类型转换成byte类型，超过256自动进一
     * @param num
     * @return
     */
    public static int[] int2bytes(int num){
        return new int[]{(byte) (num >> 8 & 0xFF),(byte)(num & 0xFF)};
    }

    /**
     * byte类型转换成心态类型
     * @param bs
     * @return
     */
    public static short bytes2int(int[] bs){
        if(bs.length >= 2){
            return (short)((bs[0] & 0xFF) << 8 | 0xFF & bs[1]);
        }
        throw new BusException("{seymour.BaseUtils} bytes length not less 2");
    }

    /**
     * 以：分隔的int类型字符串转化为二进制
     * @param ints
     * @return
     */
    public static String ints2binary(String ints){
        StringBuffer sb = new StringBuffer();

        String[] intstrs = ints.split(":");
        int num = 0;
        for(int i =0;i < intstrs.length;i++){
            num = Integer.valueOf(intstrs[i]).intValue();
            sb.append(HexUtils.byteToBit((byte)num));
        }
        return sb.toString();
    }

    /**
     * 对象数组添加元素
     * @param objs
     * @param ele
     * @return
     */
    public static Object[] addArray(Object[] objs, String... ele)
    {
        return ArrayUtils.addAll(objs, ele);
    }

    /**
     * int类型数组添加元素
     * @param contents
     * @param ele
     * @return
     */
    public static int[] addArray(int[] contents, int... ele)
    {
        return ArrayUtils.addAll(contents, ele);
    }

    /**
     * int byte类型互转
     * @param value
     * @return
     */
    public static byte[] intToBytes(int value)
    {
        byte[] src = new byte[4];
        src[3] = ((byte)(value >> 24 & 0xFF));
        src[2] = ((byte)(value >> 16 & 0xFF));
        src[1] = ((byte)(value >> 8 & 0xFF));
        src[0] = ((byte)(value & 0xFF));
        return src;
    }

    /**
     * int byte类型互转
     * @param value
     * @return
     */
    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = ((byte)(value >> 24 & 0xFF));
        src[1] = ((byte)(value >> 16 & 0xFF));
        src[2] = ((byte)(value >> 8 & 0xFF));
        src[3] = ((byte)(value & 0xFF));
        return src;
    }

}
