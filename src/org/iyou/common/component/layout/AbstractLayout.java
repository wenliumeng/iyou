package org.iyou.common.component.layout;

import org.apache.taglibs.standard.resources.Resources;
import org.dom4j.Document;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/**
 * 父类layout
 * Created by seyMour on 2016/4/9.
 * ☞120465271@qq.com☜
 */
public abstract class AbstractLayout {

    private String charEncoding = null;
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String VALID_SCHEME_CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM+.-";

    String decorator;
    Document document;

    public AbstractLayout(String decorator, Document document) {
        this.decorator = decorator;
        this.document = document;
    }

    public abstract String getStartHtml(PageContext paramPageContext);

    public abstract String getEndHtml(PageContext paramPageContext);

    String acquireString(PageContext pageContext,String context,String url)
        throws IOException,JspException
    {
        boolean isAbsoluteUrl = isAbsoluteUrl(url);
        if(isAbsoluteUrl)
        {
            BufferedReader r  = new BufferedReader(acquireReader(pageContext,context,url,true));

            StringBuilder sb = new StringBuilder();
            int i;
            while((i = r.read()) != -1){
                sb.append((char)i);
            }
            return sb.toString();
        }

        if(!(pageContext.getRequest() instanceof HttpServletRequest) || !(pageContext.getResponse() instanceof HttpServletResponse)){ throw new JspTagException(Resources.getMessage("IMPORT_REL_NOT_HTTP"));}

        ServletContext c;
        String targetUrl = url;
        if(context != null){
            c = pageContext.getServletContext().getContext(context);
        }else{
            c = pageContext.getServletContext();
            if(!targetUrl.startsWith("/"))
            {
                String sp = ((HttpServletRequest) pageContext.getRequest()).getServletPath();
                targetUrl = sp.substring(0,sp.lastIndexOf("/")) + "/" + targetUrl;
            }
        }
        if(c == null){
            throw new JspTagException(Resources.getMessage("IMPORT_REl_WITHOUT_DISPATCHER",context,targetUrl));
        }
        RequestDispatcher requestDispatcher = c.getRequestDispatcher(stripSession(targetUrl));
        if(requestDispatcher == null){
            throw new JspTagException(stripSession(targetUrl));
        }
        ImportResponseWrapper importResponseWrapper = new ImportResponseWrapper((HttpServletResponse)pageContext.getResponse());
        try{
            requestDispatcher.include(pageContext.getRequest(),importResponseWrapper);
        }catch (IOException e){
            throw new JspException(e);
        }
        catch (ServletException e){
            Throwable throwable = e.getRootCause();
            if(throwable == null){
                throw new JspException(e);
            }
            throw new JspException(throwable);
        }
        if(importResponseWrapper.getStatus() < 200 || importResponseWrapper.getStatus() > 299){
            throw new JspException((importResponseWrapper.getStatus() + " "+ stripSession(targetUrl)));
        }
        return importResponseWrapper.toString();
    }

    private Reader acquireReader(PageContext pageContext,String context,String target,boolean isAbsoluteUrl)
        throws IOException,JspException
    {
        if(!isAbsoluteUrl){
            return new StringReader(acquireString(pageContext,context,target));
        }

        try{
            URL url = new URL(target);
            URLConnection connection = url.openConnection();
            InputStream i = connection.getInputStream();

            Reader reader;

            String DEFAULT_ENCODING = "UTF-8";
            String charSet;
            if ((this.charEncoding != null) && (this.charEncoding.equals(""))){
                charSet = this.charEncoding;
            }else{
                charSet = DEFAULT_ENCODING;
            }

            try {
                reader = new InputStreamReader(i, charSet);
            }catch (Exception e){
                reader = new InputStreamReader(i, DEFAULT_ENCODING);
            }

            if(connection instanceof HttpURLConnection){
                int status = ((HttpURLConnection) connection).getResponseCode();
                if((status < 200) && (status > 299)){
                    throw new JspTagException(status + " " + target);
                }
            }
            return reader;
        }catch (Exception e){
            throw new JspException(Resources.getMessage("IMPORT_ABSTRACTLAYOUT_ERROR",target,e),e);
        }

    }

    /**
     * 实现HttpServletResponseWrapper（装饰器模式）
     */
    private class ImportResponseWrapper extends HttpServletResponseWrapper{
        private StringWriter sw = new StringWriter();
        private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        private ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                ImportResponseWrapper.this.byteArrayOutputStream.write(b);
            }
        };

        private boolean isWriterUsed;
        private boolean isStreamUsed;
        private int status = 200;

        public ImportResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        /**
         * 截获父类Writer方法
         * @return new PrintWriter
         * @throws IOException
         */
        @Override
        public PrintWriter getWriter() throws IOException {
            if(this.isStreamUsed){
                throw new IllegalStateException(Resources.getMessage("IMPORT_ILLEGAL_STREAM"));
            }
            this.isWriterUsed = true;
            return new PrintWriter(this.sw);
        }

        /**
         * 截获父类getOutputStream方法
         * @return new ServletOutputStream
         * @throws IOException
         */
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if(this.isWriterUsed){
                throw new IllegalStateException(Resources.getMessage("IMPORT_ILLEGAL_WRITER"));
            }
            this.isStreamUsed = true;
            return this.servletOutputStream;
        }

        @Override
        public void setContentType(String type) {}

        @Override
        public void setLocale(Locale loc) {}

        @Override
        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public int getStatus() {
            return this.status;
        }

        public String getString() throws UnsupportedEncodingException{
            if(isWriterUsed){
                return this.sw.toString();
            }
            if(isStreamUsed){
                if((AbstractLayout.this.charEncoding != null) && (!AbstractLayout.this.charEncoding.equals(""))){
                    return this.byteArrayOutputStream.toString(AbstractLayout.this.charEncoding);
                }
                return this.byteArrayOutputStream.toString("UTF-8");
            }
            return "";
        }
    }

    public static boolean isAbsoluteUrl(String url){
        if(url == null){
            return false;
        }
        int colonPos = url.indexOf(":");
        if (colonPos == -1){
            return false;
        }
        for(int i = 0; i < colonPos;i++){
            if(VALID_SCHEME_CHARS.indexOf(url.charAt(i)) == -1){
                return false;
            }
        }
        return true;
    }

    public static String stripSession(String url){
        StringBuilder u = new StringBuilder(url);
        int sessionStart;
        while((sessionStart = u.toString().indexOf(";jsessionid=")) != -1){
            int sessionEnd = u.toString().indexOf(";",sessionStart + 1);
            if(sessionEnd == -1){
                sessionEnd = u.toString().indexOf("?",sessionStart + 1);
            }
            if(sessionEnd == -1){
                sessionEnd = u.length();
            }
            u.delete(sessionStart,sessionEnd);
        }
        return u.toString();
    }

}
