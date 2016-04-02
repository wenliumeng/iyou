package org.iyou.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import java.util.Set;

/**
 * Config基础类
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class Config {
    private static final Log log = LogFactory.getLog(Config.class);
    private static Config instance = null;
    public static final String CONFIG_PAGE_SIZE = "page.pageSize";
    public static final String CONFIG_LAYOUT_DEFAULT = "layout.decorator";
    private Properties properties;
    private Properties decorators;
    private String defaultLayout = "default";

    public static Config getInstance()
    {
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

    public String getProperty(String name){
        if (this.properties == null){
            return null;
        }
        return this.properties.getProperty(name);
    }

    public String getDecoratorsHTML()
    {
        if(this.decorators == null){
            return null;
        }
        StringBuffer htmls = new StringBuffer();
        htmls.append("<div class=\"system-decorators\">");

        Set<Object> keys = this.decorators.keySet();
        for (Object key : keys) {
            htmls.append("<div class=\"decorator-").append(key).append("\"").append(" onclick=\"changeDecorator('").append(key).append("')\"").append(">").append(this.decorators.get(key)).append("</div>");
        }
        htmls.append("</div>");
        return htmls.toString();
    }

    static Config init(){
        instance = new Config();
        return instance;
    }

    public int getIntProperty(String name){
        String value = this.properties.getProperty(name);
        try{
            return Integer.valueOf(value);
        }catch (NumberFormatException e){
            log.error("property数据类型转换失败");
        }
        return 0;
    }

    public boolean getBooleanPorperty(String name){
        String value = this.properties.getProperty(name);
        return Boolean.parseBoolean(value);
    }
}
