package com.transwarp.util;

import java.io.*;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/416:47
 **/
public class FileUtils {
    //获取文件路径，不包含扩展名
    public static String getFileNameWithoutExtension(String filePath){
        String caselsh = filePath.substring(0,filePath.lastIndexOf("."));
        return caselsh;
    }

    //将字符串写入文件
    public static void writeStringToFile(File file,String content,String encode){
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(file),true,"GBK");
            printStream.println(content);//将字符串写入文件
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            printStream.close();
        }
    }

    public static String getFileExtension(String filePath){
        String fileType=filePath.substring(filePath.lastIndexOf(".")+1);
        return fileType;
    }
}
