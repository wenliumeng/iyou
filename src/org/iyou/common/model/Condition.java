package org.iyou.common.model;

/**
 * Condition
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public abstract interface Condition {
    public static final String BETWEEN = "BETWEEN";
    public static final String LIKE = "LIKE";
    public static final String START = "START";
    public static final String END = "END";
    public static final String EQUALS = "EQUALS";
    public static final String IS_NOT_NULL = "NOT_NULL";
    public static final String IS_NULL = "IS_NULL";
    public static final String OR = "OR";
    public static final String IN = "IN";
    public static final String NOT_IN = "NOT_IN";
    public static final String NOT_EQUALS = "NOT_EQUALS";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String LEFT_EQ = "LEFT_EQ";
    public static final String RIGHT_EQ = "RIGHT_EQ";
    public static final String BETWEEN_SPLIT = "-BTW-";

    public abstract String getProperty();

    public abstract String getOperator();

    public abstract Object getValue();
}
