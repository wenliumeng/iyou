package org.iyou.common.model.hibernate;

import org.iyou.common.model.Order;

/**
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public class HibernateOrder extends org.hibernate.criterion.Order implements Order {

    private String propertyName;


    public HibernateOrder(String propertyName, boolean ascending) {
        super(propertyName, ascending);
        this.propertyName = propertyName;
    }

    @Override
    public String getProperty() {
        return this.propertyName;
    }
}
