package org.iyou.common.util;

/**
 * 拼装JSON
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class JsUtils {
    public static final int JSON_PROP_INT = 0;
    public static final int JSON_PROP_STR = 1;

    public static void main(String[] args) {
        System.out.println(propertyValue("属性","20",1));
    }

    public static String propertyValue(String property,Object value,int type){
        if((property == null) || (value == null) || (value.equals(""))){
            return "";
        }
        String valueWrap ="";
        switch (type)
        {
            case 1:
                valueWrap = "'";
                break;
        }
        StringBuffer json = new StringBuffer();
        json.append(property);
        json.append(":");
        json.append(valueWrap);
        json.append(value);
        json.append(valueWrap);
        json.append(",");
        return json.toString();
    }
}
