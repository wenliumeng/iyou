package org.iyou.common;

import org.iyou.common.util.FileUtils;

import java.net.URL;

/**
 * static constant class
 * Created by seyMour on 2016/4/12.
 * ☞120465271@qq.com☜
 */
public class Constants {
    public static final String WEB_ROOT_KEY = "youi.root";
    public static final String APP_PATH = null;
    public static String WEB_ROOT = null;
    public static String SESSION_DECORATOR = "SESSION_DECORATOR";
    public static String SESSION_THEME = "SESSION_THEME";
    public static String PARAMETER_PAGE_ID = "_pageId";
    public static final String SUCCESS_CODE = "000000";
    public static final String ACCESS_DENIED_CODE = "111111";
    public static final String ERROR_DOMAIN_VALIDATOR = "111112";
    public static final String ERROR_DEFAULT_CODE = "999999";
    public static final String PROP_CONVERTSERVICE_BEAN = "convertService.bean";
    public static final String PROP_TRANSLOG_BEAN = "transLog.bean";
    public static final String PROP_ACTIVE_MODULE = "module.active";
    public static final String PROP_SUPER_PWD = "module.pwd";
    public static final String PROP_TRACE_ERROR = "trace.log";
    public static final String PROP_LOGIN_RANDCODE = "login.randCode";
    public static final String ROLE_MODULE = "ROLE_MODULE";
    public static final String MENU_ALLOW = "1";
    public static final String MENU_NOT_ALLOW = "0";
    public static final String PAGE_URL_POSTFIX = "html";
    public static final String DATA_URL_POSTFIX = "json";
    public static final String PROPERTY_VALUE_NULL = "PROPERTY_VALUE_NULL";
    public static final String WORKFLOW_STATUS_START = "start";

    public static String getWebRoot()
    {
        if (WEB_ROOT != null) {
            return WEB_ROOT;
        }
        String path = System.getProperty("youi.root");
        if (path == null)
        {
            URL url = Constants.class.getResource("");
            path = url.getFile();
            int ptr = path.indexOf("/WEB-INF");
            if (ptr > 0)
            {
                path = path.substring(0, ptr);
                if ((path.length() == 0) || (path.endsWith("!")))
                {
                    path = null;
                }
                else
                {
                    path = FileUtils.formatFileName(path);
                    System.out.println("==> 根目录路径[" + path + "]");
                }
            }
        }
        WEB_ROOT = path;
        return WEB_ROOT;
    }

    public static void setWebRoot(String webRoot)
    {
        WEB_ROOT = webRoot;
    }

}
