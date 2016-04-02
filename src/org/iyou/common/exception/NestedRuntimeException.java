package org.iyou.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 封装spring中的NestedRunntimeException和自己写的Exception
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class NestedRuntimeException extends org.springframework.core.NestedRuntimeException {
    protected final Log log = LogFactory.getLog(getClass());

    public NestedRuntimeException(String msg) {
        super(msg);
        logTrace();
    }

    public NestedRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
        logTrace();
    }

    protected void logTrace()
    {
        ExceptionUtils.logTrace(getCause());
    }
}
