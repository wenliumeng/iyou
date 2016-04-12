package org.iyou.common.component.layout;

import org.apache.commons.lang.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 边框layout
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class BorderLayout extends AbstractLayout {
    public static final String LAYOUT_NORTH = "north";
    public static final String LAYOUT_WEST = "west";
    public static final String LAYOUT_EAST = "east";
    public static final String LAYOUT_SOUTH = "south";

    private String bodyPanelKey = "center";

    private static final String[] panelKeys = {"north", "west", "center", "east", "south"};
    private static final String[] tablePanelKeys = {"west", "center", "east"};

    private Map<String, Panel> panels;

    public BorderLayout(String decorator, Document document) {
        super(decorator, document);
        this.panels = new HashMap<String,Panel>();

        parsePanelFromDoc();
    }

    private void parsePanelFromDoc() {
        if (this.document != null) {
            List<Element> panelElements = this.document.selectNodes("layout/panel");
            panelElements.forEach(this::addPanel);
        }
    }

    private void addPanel(Element element) {
        String position = element.attributeValue("position");
        String isBody = element.attributeValue("isBody");
        String jsp = element.attributeValue("jsp");
        if ((position == null) || (ArrayUtils.indexOf(panelKeys, position) == -1)) {
            return;
        }
        if (isBody != null) {
            this.bodyPanelKey = position;
        }
        Panel panel = new Panel();
        panel.setJsp(jsp);
        panel.setWidth(element.attributeValue("width"));
        this.panels.put(position, panel);
    }

    public String getStartHtml(PageContext pageContext) {
        StringBuilder htmls = new StringBuilder();
        for (String panelKey : panelKeys) {
            if ("west".equals(panelKey)) {
                htmls.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"><tr>");
            }
            if (this.bodyPanelKey.equals(panelKey)) {
                break;
            }
            htmls.append(loadPanel(pageContext, panelKey));
        }
        Panel bodyPanel = this.panels.get(this.bodyPanelKey);
        if (bodyPanel != null) {
            htmls.append("<td valign=\"top\"><div class=\"layout-panel panel-").append(this.bodyPanelKey).append("\">");

            htmls.append(buildContentPrefix());
        }
        return htmls.toString();
    }

    public String getEndHtml(PageContext pageContext) {
        StringBuilder htmls = new StringBuilder();

        Panel bodyPanel = this.panels.get(this.bodyPanelKey);
        if (bodyPanel != null) {
            htmls.append(buildContentPostfix());
            htmls.append("</div></td>");
        }
        int counts = panelKeys.length;

        int bodyPanelIndex = ArrayUtils.indexOf(panelKeys, this.bodyPanelKey);
        for (int i = bodyPanelIndex + 1; i < counts; i++) {
            String panelKey = panelKeys[i];
            htmls.append(loadPanel(pageContext, panelKey));
            if ("east".equals(panelKey)) {
                htmls.append("</tr></table>");
            }
        }
        htmls.append("<script>$(function(){$('body',document).borderLayout({").append("type:'borderLayout'").append("});").append("$('.system-decorators .decorator-").append(this.decorator).append("').addClass('selected');").append("});</script>");

        return htmls.toString();
    }

    private String loadPanel(PageContext pageContext, String panelKey) {
        StringBuilder htmls = new StringBuilder();
        String basePath = "/decorators/" + this.decorator + "/";
        if (this.panels.containsKey(panelKey)) {
            Panel panel = this.panels.get(panelKey);
            String jspFile = panel.getJsp();
            if (ArrayUtils.indexOf(tablePanelKeys, panelKey) != -1) {
                htmls.append("<td class=\"td-").append(panelKey).append("\" width =\"").append(panel.getWidth()).append("\" valign = \"top\"");
            }
            htmls.append("<div style=\"width=\"").append(panel.getWidth()).append("px;\" class=\"layout-panel panel-").append(panelKey).append("\">");
            if (jspFile != null) {
                try {
                    htmls.append(acquireString(pageContext, null, basePath + "/" + jspFile));
                } catch (IOException | JspException e) {
                    e.printStackTrace();
                }
            }
            htmls.append("</div>");
            if (ArrayUtils.indexOf(tablePanelKeys, panelKey) != -1) {
                htmls.append("</td>");
                if ("west".equals(panelKey)) {
                    htmls.append("<td class=\"page-split-y\"><div></div></td>");
                }
            }
        }
        return htmls.toString();
    }


    protected String buildContentPrefix() {
        return "<div class=\"main-navigator\">" + " \t<div class=\"main-navigator-right\">" + "\t\t<div id=\"system-title\" class=\"main-navigator-content\"></div>" + "\t</div>" + "</div>" + "<div class=\"main-body\">" + "\t<div class=\"main-body-right\">" + "\t\t<div class=\"youi-page-content adapt-width\">" + "";
    }

    protected String buildContentPostfix() {
        return "\t</div></div>" + "</div>" + "<div class=\"main-footer\">" + "\t<div class=\"main-footer-right\">" + "\t\t<div class=\"main-footer-content\"></div>" + "\t</div>" + "</div>";
    }


}
