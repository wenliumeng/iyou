package org.iyou.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iyou.common.config.Config;
import org.iyou.common.web.view.Message;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by seyMour on 2016/4/1.
 * ☞120465271@qq.com☜
 */
public class ExceptionUtils {
    private static final Log log = LogFactory.getLog(ExceptionUtils.class);

    public static Message getErrorMessage(Exception ex)
    {
        if(ex instanceof ExceptionMessage){
            return ((ExceptionMessage)ex).getExceptionMessage();
        }
        String info = "系统异常："+ex.getMessage();
        logTrace(ex);
        return new Message("999999",info);
    }

    /**
     * 异常信息转为字符串
     * @param cause
     */
    public static void logTrace(Throwable cause){
        String traceError = Config.getInstance().getProperty("trace.log");
        if(traceError.equals("true") && (cause != null))
        {
            ByteArrayOutputStream byos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(byos);
            cause.printStackTrace(ps);
            String trace = byos.toString();
            log.error(trace);
        }
    }
}
