package org.iyou.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by seyMour on 2016/3/30.
 * ☞120465271@qq.com☜
 */
public class DateUtils {

    public static void main(String[] args) {


        System.out.println(getYearCode());
    }

    /**
     * 根据格式获取时间字符串
     * <font color="red">支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br></font>
     *
     * @param format String 字符串<br>
     * @return String 日期<br>
     */
    public static String getToday(String format) {
        return (new SimpleDateFormat(format)).format((new GregorianCalendar()).getTime());
    }

    /**
     * 获取默认时间字符串
     *
     * @return
     */
    public static String getToday() {
        return getToday("yyyy-MM-dd");
    }

    /**
     * 取得当前年的后两位
     *
     * @return 16
     */
    public static String getYearCode() {
        return (new Integer(Calendar.getInstance().get(1)).toString().substring(2, 4));
    }
}
