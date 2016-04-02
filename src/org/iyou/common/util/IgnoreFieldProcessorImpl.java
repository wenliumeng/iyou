package org.iyou.common.util;

import net.sf.json.JSONObject;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.springframework.security.util.FieldUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jSON解析
 * Created by seyMour on 2016/3/31.
 * ☞120465271@qq.com☜
 */
public class IgnoreFieldProcessorImpl implements PropertyFilter {

    private static final Log log = LogFactory.getLog(IgnoreFieldProcessorImpl.class);
    private String[] fileds;
    private String[] notIgnoreColls;
    private boolean ignoreColl = false;

    public IgnoreFieldProcessorImpl() {
        if(log.isDebugEnabled()){
            log.debug("json字段装换忽略字段设置");
        }
    }

    public IgnoreFieldProcessorImpl(String[] fileds) {
        this.fileds = fileds;
    }

    public IgnoreFieldProcessorImpl(boolean ignoreColl) {
        this.ignoreColl = ignoreColl;
    }

    public IgnoreFieldProcessorImpl(boolean ignoreColl, String[] fileds) {
        this.ignoreColl = ignoreColl;
        this.fileds = fileds;
    }

    public String[] getNotIgnoreColls() {
        return notIgnoreColls;
    }

    public void setNotIgnoreColls(String[] notIgnoreColls) {
        this.notIgnoreColls = notIgnoreColls;
    }

    public String[] getFileds() {
        return fileds;
    }

    public void setFileds(String[] fileds) {
        this.fileds = fileds;
    }

    public boolean isIgnoreColl() {
        return ignoreColl;
    }

    public void setIgnoreColl(boolean ignoreColl) {
        this.ignoreColl = ignoreColl;
    }

    @Override
    public boolean apply(Object source, String name, Object value) {
        if((source instanceof JSONObject)){
            return false;
        }
        if(name.equals("refModel")){
            return true;
        }
        Field declaredField;
        try{
            declaredField = FieldUtils.getField(source.getClass(),name);
        }catch (IllegalStateException e ){
            declaredField = null;
        }
        if(declaredField == null){
            return true;
        }
        Class<?> type = declaredField.getType();
        if(type == null){
            return true;
        }
        if((declaredField != null) && (this.ignoreColl)){
            if(this.notIgnoreColls != null){
                for(String notIgnoreColls : this.notIgnoreColls){
                    if(declaredField.getName().equals(notIgnoreColls)){
                        return false;
                    }
                }
            }
            if(type == Collection.class || type == Set.class || type == Map.class || type == List.class){
                return true;
            }
        }
        if((type == byte[].class) || (type == Document.class)){
            return true;
        }
        if(name.equals("hibernateLazyInitializer")){
            return true;
        }
        if((this.fileds != null)&&(this.fileds.length > 0 )){}
        return false;
    }
}
