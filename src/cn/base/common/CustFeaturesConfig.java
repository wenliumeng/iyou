package cn.base.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 基础配置类
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class CustFeaturesConfig {
    public static final String GATEWAY_IP = "gatewayip";
    public static final String GATEWAY_PORT = "gatewayport";
    public static final String MSG_APPKEY_AR = "msg.appkey.ar";
    public static final String MSG_APPSECRET_AR = "msg.appsecret.ar";
    public static final String MSG_APPKEY_IOS = "msg.appkey.ios";
    public static final String MSG_APPSECRET_IOS = "msg.appsecret.ios";
    public static final String SMS_ACOUNT = "sms.account";
    public static final String SMS_PWD = "sms.pwd";
    public static final String SMS_PRODUCT = "sms.product";
    public static final String SMS_VERIFICATION_JUSTNUMBER = "sms.verification.justnumber";
    public static final String SMS_VERIFICATION_LENGTH = "sms.verification.length";
    public static final String SMS_DESCRIPTION_SUFFIX = "sms.description.suffix";
    public static final String TASK_LOOP_TIMES = "task.loop.times";
    public static final String TASK_LOOP_INTERVAL = "task.loop.interval";
    public static final String ALARM_FORMAT = "alarm.format";
    protected  final Log log = LogFactory.getLog(CustFeaturesConfig.class);
    private static CustFeaturesConfig config = null;
    private ResourceBundle resourceBundle ;

    public CustFeaturesConfig() {
        Locale locale = LocaleContextHolder.getLocale();
        this.resourceBundle = ResourceBundle.getBundle("custfeature",locale);
    }

    public static CustFeaturesConfig getInstance(){
        if(config == null){
            config = new CustFeaturesConfig();
        }
        return config;
    }

    public Integer getIntegerConfig(String key){
        return Integer.valueOf(getConfig(key));
    }

    public Long getLongConfig(String key){
        return Long.valueOf(getConfig(key));
    }

    public String getConfig(String key){
        String config;
        try{
            config = this.resourceBundle.getString(key);
        }catch (MissingResourceException mse){
            config = "";
            this.log.info(key + "未找到！");
        }
        return config;
    }

    public void dispose(){
        config = null;
        this.resourceBundle = null;
    }
}
