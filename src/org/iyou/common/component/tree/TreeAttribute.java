package org.iyou.common.component.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xxx
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 * Target作用域 Retention.Runtimejvm保留，能被反射使用
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeAttribute {
    public static final String TREE_ATTR_ID = "id";
    public static final String TREE_ATTR_CODE = "code";
    public static final String TREE_ATTR_PARENT = "parent";
    public static final String TREE_ATTR_PID = "parentId";
    public static final String TREE_ATTR_TEXT = "text";
    public static final String TREE_ATTR_GOURP = "group";
    public static final String TREE_ATTR_SRC = "src";

    String value();
}
