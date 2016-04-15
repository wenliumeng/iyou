package org.iyou.common.model.annotation;

import jdk.nashorn.internal.ir.annotations.Reference;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 过滤操作注解
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterOperator {
    String operator();
}
