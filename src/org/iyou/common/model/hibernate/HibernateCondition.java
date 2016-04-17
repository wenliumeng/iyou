package org.iyou.common.model.hibernate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.iyou.common.model.Condition;

/**
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public class HibernateCondition implements Condition {
    protected String property;
    protected String operator;
    protected Object value;

    public HibernateCondition(String property, String operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public Criterion generateExpression(String alias) {
        if (this.value != null) {
            if (this.operator.equals("EQUALS")) {
                return Restrictions.eq(this.property, this.value);
            }
            if (this.operator.equals("NOT_EQUALS")) {
                return Restrictions.ne(this.property, this.value);
            }
            if (this.operator.equals("LIKE")) {
                return Restrictions.like(this.property, this.value.toString(), MatchMode.ANYWHERE);
            }
            if (this.operator.equals("END")) {
                return Restrictions.like(this.property, this.value.toString(), MatchMode.END);
            }
            if (this.operator.equals("START")) {
                return Restrictions.like(this.property, this.value.toString(), MatchMode.START);
            }
            if (this.operator.equals("BETWEEN")) {
                String[] betweenArray = this.value.toString().split("-BTW-");
                if (betweenArray.length < 2) {
                    return null;
                }
                return generateBetween(betweenArray[0], betweenArray[1]);
            }
            if (this.operator.equals("IN")) {
                if ((this.value instanceof Object[])) {
                    return Restrictions.in(this.property, (Object[]) this.value);
                }
            } else if (this.operator.equals("NOT_IN")) {
                if ((this.value instanceof Object[])) {
                    return Restrictions.not(Restrictions.in(this.property, (Object[]) this.value));
                }
            } else {
                if (this.operator.equals("LEFT")) {
                    return Restrictions.lt(this.property, this.value);
                }
                if (this.operator.equals("RIGHT")) {
                    return Restrictions.gt(this.property, this.value);
                }
                if (this.operator.equals("LEFT_EQ")) {
                    return Restrictions.le(this.property, this.value);
                }
                if (this.operator.equals("RIGHT_EQ")) {
                    return Restrictions.ge(this.property, this.value);
                }
            }
        }
        if (this.operator.equals("IS_NULL")) {
            return Restrictions.isNull(this.property);
        }
        if (this.operator.equals("NOT_NULL")) {
            return Restrictions.isNotNull(this.property);
        }
        return null;
    }

    public Criterion generateExpression() {
        return generateExpression(null);
    }

    private Criterion generateBetween(String begin, String end) {
        return Restrictions.between(this.property, begin, end);
    }

    @Override
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.property + " " + this.operator + " " + this.value;
    }

}
