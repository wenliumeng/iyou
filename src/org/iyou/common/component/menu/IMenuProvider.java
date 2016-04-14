package org.iyou.common.component.menu;


import org.iyou.common.model.Condition;
import org.iyou.common.model.Order;

import java.util.Collection;
import java.util.List;

/**
 * menu供给器接口
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public abstract interface IMenuProvider {
    public abstract List<IMenu> getMenus(Collection<Condition> paramCollection, Collection<Order> paramCollection1);
}
