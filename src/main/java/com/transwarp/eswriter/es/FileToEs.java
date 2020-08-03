package com.transwarp.eswriter.es;

import com.transwarp.eswriter.util.ESUtil;
import com.transwarp.util.CommonUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.transwarp.eswriter.util.FileUtil.fileToBytes;

public class FileToEs {

    private static Logger log = Logger.getLogger(FileToEs.class);

    public static void writeToEs(String path) {

        String begintime = "";
        try {
            ESDataFormate esData = fileToBytes(path);
            Long nowTime = System.currentTimeMillis();
            String format = "yyyy-MM-dd-HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            begintime = sdf.format(new Date(Long.valueOf(nowTime)));
            String uuid = CommonUtil.getStringUUID();
            ESUtil.writeToEs(esData, uuid);
            //FileUtil.fileRename(path, begintime + FileUtil.fileSuffixDone);
        } catch (Exception e) {
            log.error("", e);
            //FileUtil.fileRename(path, begintime + FileUtil.fileSuffixFail);
        }
    }

    public static void writeToEs(File file, String uuid) {

        try {
            ESDataFormate esData = fileToBytes(file);
            ESUtil.writeToEs(esData, uuid);
        } catch (Exception e) {
            log.error("", e);
        }

    }

    public static void writeImportToEs(File file, String uuid, String nodeId) {

        try {
            ESDataFormate esData = fileToBytes(file);
            ESUtil.writeImportToEs(esData, uuid, nodeId);
        } catch (Exception e) {
            log.error("", e);
        }

    }

}
