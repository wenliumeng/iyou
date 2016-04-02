package org.iyou.common.web.view;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class Message {
    public String info;
    public String code;

    public Message(String info, String code) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
