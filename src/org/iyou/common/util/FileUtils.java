package org.iyou.common.util;

/**
 * Created by seyMour on 2016/3/26.
 * ☞120465271@qq.com☜
 */
public class FileUtils {
    public FileUtils() {}

    /**
     * Out of the filename's %20
     * @param fileName
     * @return
     */
    public static String formatFileName(String fileName){
        char[] content = new char[fileName.length()];
        char[] result = new char[fileName.length()];
        fileName.getChars(0,fileName.length(),content,0);

        int len = 0;
        for(int i = 0; i < content.length;i++){
            if((content[i] == '%') && (content[i+1] == '2') && (content[i+2] == '0'))
            {
                result[(len++)] = ' ';
                i += 2;
            }
            else
            {
                result[(len++)] = content[i];
            }
        }
        return String.valueOf(result,0,len);
    }
}
