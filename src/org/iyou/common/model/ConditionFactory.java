package org.iyou.common.model;

import org.iyou.common.model.hibernate.HibernateCondition;
import org.iyou.common.model.hibernate.HibernateOrder;

/**
 * xxx
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public class ConditionFactory {
    private static ConditionFactory conditionFactory = null;

    public static ConditionFactory getInstance()
    {
        if(conditionFactory == null){
            conditionFactory = new ConditionFactory();
        }
        return conditionFactory;
    }

    public Condition getCondition(String property,String operator,Object value){
        return new HibernateCondition(property,operator,value);
    }

    public Order getOrder(String propertyName, boolean ascending) {
        return new HibernateOrder(propertyName, ascending);
    }
}
