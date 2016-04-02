package org.iyou.common.exception;

import org.iyou.common.web.view.Message;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class BusException extends NestedRuntimeException implements ExceptionMessage{

    private String code;

    public BusException(String msg) {
        this("999999",msg);
    }

    public BusException(String code,String msg) {
        super(msg);
        this.code = code;
    }

    public BusException(String msg, Throwable cause) {
        this("999999",msg,cause);
    }

    public BusException(String code,String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    @Override
    public Message getExceptionMessage() {
        return new Message(this.code,getMessage());
    }
}
