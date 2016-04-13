package org.iyou.common.web.taglib.menu;

import javax.servlet.http.HttpServletRequest;

/**
 * Menu父类
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 */
public abstract class MenuHtml {
    public abstract String getHtml(HttpServletRequest paramHttpServletRequest,String paramString);
}
