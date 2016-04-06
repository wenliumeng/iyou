package cn.utils;

/**
 * Created by seyMour on 2016/4/6.
 * ☞120465271@qq.com☜
 */
public class HexUtils {
    /**
     * byte转化为Bit
     * @param b
     * @return
     */
    public static String byteToBit(byte b){
        return ""+(byte)((b >> 7) & 0x1) + (byte)((b >> 6) & 0x1) + (byte)((b >> 5) & 0x1) + (byte)((b >> 4) & 0x1) + (byte)((b >> 3) & 0x1) + (byte)((b >> 2) & 0x1) + (byte)((b >> 1) & 0x1) + (byte)((b >> 0) & 0x1);
    }

}
