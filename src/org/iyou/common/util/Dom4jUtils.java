package org.iyou.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * Created by seyMour on 2016/3/31.
 * ☞120465271@qq.com☜
 */
public class Dom4jUtils {
    private static final Log log = LogFactory.getLog(Dom4jUtils.class);

    /**
     * 解析xml
     * @param filepath
     * @return
     */
    public static Document saxParse(String filepath)
    {
        Document doc = null;
        SAXReader saxReader = new SAXReader();
        try
        {
            doc = saxReader.read(filepath);
        } catch (DocumentException e){
            log.error("SAXReader解析xml文件-"+filepath+"- 异常：" + e.getMessage());
        }
        return doc;
    }

    /**
     * xml写入到文件流中
     * @param filePath
     * @param doc
     * @param encoding
     */
    public static void writeFormatDocToFile(String filePath,Document doc,String encoding){
        OutputFormat format = null;
        XMLWriter output = null;
        try{
            format = OutputFormat.createPrettyPrint();
            format.setEncoding(encoding);
            format.setOmitEncoding(false);
            output = new XMLWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"utf-8")),format);
        }catch (IOException e){
            log.error("xml文件IO写入错误：" + e.getMessage());
        }
        finally {
            try
            {
                output.close();
            }catch (IOException e){
                log.info("文件关闭失败：" + e.getMessage());
            }
        }
    }

    /**
     * 节点添加属性
     * @param modeELement
     * @param attrName
     * @param attrValue
     */
    public static void addAttribute(Element modeELement,String attrName,String attrValue){
        if(attrValue != null){
            modeELement.addAttribute(attrName,attrValue);
        }
    }

    /**
     * 字符串xml解析成doc
     * @param xmlText
     * @return
     */
    public static Document parseText(String xmlText)
    {
        Document doc;
        try{
            doc = DocumentHelper.parseText(xmlText);
        }catch (DocumentException e)
        {
            doc = null;
        }
        return doc;
    }

}
