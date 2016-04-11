package cn.base.common.security;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 动态登陆失败，参数返回
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(httpServletResponse.getOutputStream(),JsonEncoding.UTF8);

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.element("code","000000");
            jsonObject.element("info",e.getMessage());
            objectMapper.writeValue(jsonGenerator,jsonObject);
        } catch (JsonProcessingException ex){
            throw new HttpMessageNotWritableException("Could not write JSON:" + ex.getMessage(),ex);
        }

    }
}
