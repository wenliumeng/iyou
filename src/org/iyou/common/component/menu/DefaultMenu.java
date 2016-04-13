package org.iyou.common.component.menu;

import org.iyou.common.component.tree.TreeAttribute;

/**
 * 默认Menu
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 */
public class DefaultMenu implements IMenu {
    private String menuIconStyle;
    private String menuId;
    private String src;
    private String target;
    private String parentMenuId;
    private String menuName;

    @Override
    public String getMenuIconStyle() {
        return menuIconStyle;
    }

    public void setMenuIconStyle(String menuIconStyle) {
        this.menuIconStyle = menuIconStyle;
    }

    @TreeAttribute("id")
    @Override
    public String getMenuId() {
        return menuId;
    }

    @Override
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String getParentMenuId() {
        return parentMenuId;
    }

    @TreeAttribute("parent")
    @Override
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    @TreeAttribute("text")
    @Override
    public String getMenuName() {
        return menuName;
    }

    @Override
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}