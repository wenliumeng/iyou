package cn.base.common;


import org.dom4j.Document;
import org.iyou.common.component.layout.BorderLayout;

/**
 * 定制layout
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class CustomBorderLayout extends BorderLayout {
    public CustomBorderLayout(String decorator, Document document) {
        super(decorator, document);
    }

    protected String buildContentPostfix(){
        return "</div>";
    }

    protected String buildContentPrefix(){
        return "<div id=\"layout-main\" class=\"youi-page-content ui-widget ui-widget-content ui-corner-all ui-dialog-content\">";
    }
}
