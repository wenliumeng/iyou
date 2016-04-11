package org.iyou.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 登陆验证工具类
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class PrincipalUtils {
    private static final Log log = LogFactory.getLog(PrincipalUtils.class);
    private static final String PRINCIPAL_PROPERTY_PREFIX = "principal.logonUser";

    public static String getPrincipalValue(String property){
        //http的authentication和authorization机制，授权与认证机制
        //authentication主要有HTTPBasic和Digest两种方式
        //HTTPBasic就是在http请求中直接加入base64编码的用户名和密码，所以通常使用https会安全些，如果截获http请求，再次攻击那就是
        // replay attack了，
        //Digest是每次返回到客户端的数据都是用时间戳混淆的，再次截获后发出的数据就不一样，服务器就会不认同
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if((property != null) && (auth != null) && (auth.getPrincipal() != null)){
            Object value = null;
            BeanWrapperImpl wrapper = new BeanWrapperImpl(auth);
            try{
                value = wrapper.getPropertyValue("pricipal.logonUser."+property);
            }catch (BeansException e){
                log.warn("登陆对象属性{"+property+"}取值异常！");
            }
            return value != null ? value.toString() : null;
        }
        return null;
    }
}
