package org.iyou.common.util;


import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.iyou.common.domain.Domain;
import org.iyou.common.model.Condition;
import org.iyou.common.model.ConditionFactory;
import org.iyou.common.model.Order;
import org.iyou.common.model.hibernate.HibernateCondition;
import org.iyou.common.model.hibernate.HibernateOrder;

/**
 * Created by seyMour on 2016/3/31.
 * ☞120465271@qq.com☜
 */
public class ConditionUtils {
    public static Collection<Condition> getConditions(String prefix, Domain bean, BindingResult result, Map<String, String> filterMap, Map<String, String[]> parameterMap) {
        Collection<Condition> conditions = new ArrayList();
        if (prefix != null) {
            prefix = prefix + ".";
        } else {
            prefix = "";
        }
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            if (!"class".equals(name)) {
                String propertyName = prefix + name;

                Transient tsi = (Transient) propertyDescriptor.getReadMethod().getAnnotation(Transient.class);
                if (tsi == null) {
                    Object value = result.getFieldValue(propertyName);
                    String operator = (String) filterMap.get(propertyName);
                    if (operator == null) {
                        operator = "EQUALS";
                    }
                    if ((value != null) && (!value.equals(""))) {
                        if ((value instanceof Domain)) {
                            conditions.addAll(getConditions(propertyName, (Domain) value, result, filterMap, parameterMap));
                        } else if (!(value instanceof Set)) {
                            if (!(value instanceof Collection)) {
                                if (!(value instanceof Map)) {
                                    if (parameterMap.containsKey(propertyName)) {
                                        conditions.add(ConditionFactory.getInstance().getCondition(propertyName, operator, value));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return conditions;
    }

    public static Condition getCondition(String propertyName, String operator, Object value) {
        return new HibernateCondition(propertyName, operator, value);
    }

    public static Order getOrder(String propertyName, boolean ascending) {
        return new HibernateOrder(propertyName, ascending);
    }
}
