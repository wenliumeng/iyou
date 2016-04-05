package org.iyou.common.util;

import cn.utils.MeBaseUtils;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.iyou.common.domain.Domain;

import java.util.Date;
import java.util.List;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class JsonUtils {
    public static JsonConfig getJsonConfig(List<? extends Domain> list){
        JsonConfig jsonConfig = new JsonConfig();
        if(list != null && list.size() > 0){
                return defaultJsonConfig(true);
        }
        return jsonConfig;
    }

    public static JsonConfig getJsonConfig(List<? extends Domain> list,String[] notIgnoreColls){
        JsonConfig jsonConfig = new JsonConfig();
        IgnoreFieldProcessorImpl ignoreFieldProcessor = new IgnoreFieldProcessorImpl(true);
        ignoreFieldProcessor.setNotIgnoreColls(notIgnoreColls);
        jsonConfig.setJsonPropertyFilter(ignoreFieldProcessor);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return null;
    }

    private static JsonConfig defaultJsonConfig(boolean ignoreColl){
        JsonConfig jsonConfig = new JsonConfig();
        //设置json需要忽略的对象
        jsonConfig.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(ignoreColl));
        //屏蔽自包含
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        //java中的时间类型转换
        jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        //过滤不需要的属性
        jsonConfig.setExcludes(new String[]{"hibernateLazyInitializer"});
        return jsonConfig;
    }
}
