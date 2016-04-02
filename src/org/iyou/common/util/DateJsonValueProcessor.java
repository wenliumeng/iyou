package org.iyou.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seyMour on 2016/3/31.
 * ☞120465271@qq.com☜
 */
public class DateJsonValueProcessor implements JsonValueProcessor{

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateFormat dateFormat;

    public DateJsonValueProcessor(String datePattern) {
        try{
            this.dateFormat = new SimpleDateFormat(datePattern);
        }catch (Exception e) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    private Object process(Object value){
        if(value == null) {
            return null;
        }
        return this.dateFormat.format((Date)value);
    }
}
