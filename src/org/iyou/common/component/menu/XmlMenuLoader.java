package org.iyou.common.component.menu;

import org.dom4j.Document;
import org.dom4j.Element;
import org.iyou.common.Constants;
import org.iyou.common.util.Dom4jUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * menu加载类
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 */
public class XmlMenuLoader {

    private static final XmlMenuLoader instance = new XmlMenuLoader();

    private static Collection<IMenu> menus;

    private static Collection<IMenu> moduleMenus;

    private XmlMenuLoader() {
        moduleMenus = Collections.synchronizedCollection(new ArrayList());
        menus = Collections.synchronizedCollection(new ArrayList());
        loadXmlMenu();
        loadModuleXmlMenu();
    }

    public static XmlMenuLoader getInstance(){
        return instance;
    }

    public Collection<IMenu> getMenus(){
        return menus;
    }

    public Collection<IMenu> getModuleMenus() {
        return moduleMenus;
    }

    public IMenu getMenu(String menuId){
        for(IMenu menu : menus){
            if(menuId.equals(menu.getMenuId())){
                return menu;
            }
        }
        return null;
    }

    public void reload(){
        menus.clear();
        loadXmlMenu();
    }

    private List<IMenu> getXmlMenus(Document doc){
        List<IMenu> menus = new ArrayList<>();
        if(doc != null){
            List<Element> menuElements = doc.selectNodes("//menu");
            for(Element element : menuElements){
                IMenu menu = new DefaultMenu();
                menu.setMenuId(element.attributeValue("id"));
                menu.setMenuName(element.attributeValue("text"));
                menu.setSrc(element.attributeValue("href"));
                if(element.getParent().getName().equals("menu")){
                    menu.setParentMenuId(element.getParent().attributeValue("id"));
                }
                menu.setTarget(element.attributeValue("target"));
                menus.add(menu);
            }
        }
        return menus;
    }

    private void loadXmlMenu(){
        Document doc = null;
        String webRoot = Constants.getWebRoot();
        String xmlPath = webRoot + "/WEB-INF/configs/menu/menu.xml";
        doc = Dom4jUtils.saxParse(xmlPath);
        if(doc != null){
            menus.addAll(getXmlMenus(doc));
        }
    }

    private void loadModuleXmlMenu(){
        Document doc = null;
        String webRoot = Constants.getWebRoot();
        String xmlPath = webRoot +"/WEB-INF/configs/menu/menu-module.xml";
        doc = Dom4jUtils.saxParse(xmlPath);
        if(doc != null){
            menus.addAll(getXmlMenus(doc));
        }
    }
}
