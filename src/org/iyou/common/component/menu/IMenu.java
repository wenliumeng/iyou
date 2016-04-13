package org.iyou.common.component.menu;

import org.iyou.common.domain.Domain;

/**
 * Menu interface
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 */
public abstract interface IMenu extends Domain{

    public abstract String getMenuId();

    public abstract void setMenuId(String menuId);

    public abstract String getSrc();

    public abstract void setSrc(String src);

    public abstract String getTarget();

    public abstract void setTarget(String target);

    public abstract String getMenuIconStyle();

    public abstract String getMenuName();

    public abstract void setMenuName(String menuName);

    public abstract String getParentMenuId();

    public abstract void setParentMenuId(String parentMenuId);

}
