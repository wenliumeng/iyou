package org.iyou.common.util;

import java.util.List;

import org.iyou.common.web.taglib.button.Button;


public class HtmlUtils {
    public static final int HTML_ATTR_INT = 0;
    public static final int HTML_ATTR_STR = 1;

    public static String attrHtml(String attrName, String attrValue) {
        if ((attrName == null) || (attrName.equals(""))) return "";
        String valueWrap = "\"";
        return " " + attrName + "=" + valueWrap + attrValue + valueWrap;
    }


    public static String attrJson(String attrName, Object attrValue, int type) {
        if ((attrName == null) || (attrName.equals("")) || (attrValue == null)) return "";
        String valueWrap = "";
        switch (type) {
            case 1:
                valueWrap = "'";
                break;
        }


        return attrName + ":" + valueWrap + attrValue + valueWrap;
    }

    public static String domHtmlPrefix(String domName) {
        StringBuffer htmls = new StringBuffer();

        return htmls.toString();
    }


    public static String generateButtonHtml(Button button) {
        String icon = button.getIcon();
        int active = button.getActive();
        String id = "button_" + button.getName();
        StringBuffer htmls = new StringBuffer();
        htmls.append("<div id=\"" + id + "\" class=\"youi-button " + "active-" + active + "\">");
        htmls.append("<div class=\"right\"><div class=\"center\">");
        htmls.append("<a href=\"#\" class=\"" + button.getName() + (icon != null ? " icon " + icon : "") + "\">");
        htmls.append(button.getCaption());
        htmls.append("</a></div>");
        htmls.append("</div>");
        htmls.append("</div>");
        return htmls.toString();
    }


    public static String generateButtonsHtml(List<Button> buttons) {
        StringBuffer htmls = new StringBuffer();
        htmls.append("<table><tr>");
        for (Button button : buttons) {
            htmls.append("<td>");
            htmls.append(generateButtonHtml(button));
            htmls.append("</td>");
        }
        htmls.append("</tr></table>");
        return htmls.toString();
    }

    public static String generateVerticalButtonsHtml(List<Button> buttons) {
        StringBuffer htmls = new StringBuffer();
        htmls.append("<table>");
        for (Button button : buttons) {
            htmls.append("<tr><td>");
            htmls.append(generateButtonHtml(button));
            htmls.append("</td></tr>");
        }
        htmls.append("</table>");
        return htmls.toString();
    }


    public static String wrapWithPanel(String title, String html) {
        StringBuffer htmls = new StringBuffer();
        htmls.append(panelPrefix(title));
        htmls.append(html);
        htmls.append(panelPostfix());
        return htmls.toString();
    }


    public static String panelPrefix(String title) {
        title = title == null ? "" : title;
        StringBuffer htmls = new StringBuffer();
        htmls.append("<div class=\"youi-panel\">");
        htmls.append("<div class=\"panel-header\">");
        htmls.append("\t<div>" + title + "</div>");
        htmls.append("</div>");
        htmls.append("<div class=\"panel-content\">");
        return htmls.toString();
    }


    public static String panelPostfix() {
        StringBuffer htmls = new StringBuffer();
        htmls.append("</div>");
        htmls.append("</div>");
        return htmls.toString();
    }
}


