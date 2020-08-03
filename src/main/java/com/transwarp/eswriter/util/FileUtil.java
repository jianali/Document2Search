package com.transwarp.eswriter.util;

import com.transwarp.eswriter.es.ESDataFormate;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtil {
    private static Logger log = Logger.getLogger(FileUtil.class);
    public static String fileSuffixDone = ".done";
    public static String fileSuffixFail = ".fail";
    public static List<String> formatSupports = Arrays.asList(GetProperties.FILE_FROMAT_SUPPORT.split(","));
    public static Set<String> traverseFolder(String path) {
        Set<String> diskFiles = new HashSet<String>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return diskFiles;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        /*if (!file2.getAbsolutePath().endsWith(fileSuffixDone) && !file2.getAbsolutePath().endsWith(fileSuffixFail)
                                && !file2.getAbsolutePath().endsWith(".DS_Store") && !FileUtil.checkFileWriting(file2.getAbsolutePath())) {
                            log.info("发现新文件:" + file2.getAbsolutePath());
                            //ProducerSet.getUnDoneFiles().add(file2.getAbsolutePath());
                            diskFiles.add(file2.getAbsolutePath());
                        }*/
                        //判断文件格式是否支持，文件是否正在写入
                        if (isFormatSupports(getFileFormat(file2.getAbsolutePath())) &&
                                ! FileUtil.checkFileWriting(file2.getAbsolutePath()) &&
                                ! getFileName(file2.getAbsolutePath()).startsWith("~") ) {
                            log.info("发现新文件:" + file2.getAbsolutePath());
                            diskFiles.add(file2.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            log.error(path+" 目录不存在!");
        }
        return diskFiles;
    }

    public static void fileRename(String path,String suffix){
        File fileOld = new File(path);
        File fileNew = new File(path +  suffix);
        fileOld.renameTo(fileNew);
    }

    public static boolean checkFileWriting (String fileName) {
        long oldLen = 0;
        long newLen = 0;
        File file = new File(fileName);
        oldLen = file.length();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newLen = file.length();
        if (oldLen == newLen)
            return false;
        else
            return true;
    }


    public static ESDataFormate fileToBytes(String path) throws IOException {
        File file = new File(path);
        path = file.getAbsolutePath();
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        }catch (Exception e){
        }

        String fileName = FileUtil.getFileName(path);
        ESDataFormate esData = new ESDataFormate(fileName, fileContent, "", path);
        return esData;
    }

    public static ESDataFormate fileToBytes(File file) throws IOException{

        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        }catch (Exception e) {

        }

        String fileName = file.getName();
        ESDataFormate esDate = new ESDataFormate(fileName, fileContent, "", "");
        return esDate;
    }

    public static boolean isFormatSupports(String format){
        if (formatSupports.contains(format.toLowerCase()))
            return true;
        else
            return false;
    }

    public static String getFileFormat(String file){
        return file.substring(file.lastIndexOf(".")+1);
    }
    public static String getFileName(String file){
        return file.substring(file.lastIndexOf("/")+1);
    }
}
