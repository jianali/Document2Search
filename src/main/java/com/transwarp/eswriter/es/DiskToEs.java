package com.transwarp.eswriter.es;

import com.transwarp.eswriter.util.GetProperties;
import com.transwarp.eswriter.util.FileUtil;
import com.transwarp.eswriter.util.ESUtil;
import org.apache.log4j.Logger;
import com.transwarp.util.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class DiskToEs {

    private static Logger log = Logger.getLogger(DiskToEs.class);

    public static void writeToEs(String dirPath) {
        String[] dirs;
        if (dirPath.equals("")) {
            dirs = GetProperties.DISK_ELASTICSEARCH_SCAN_PATH.split(",");
        } else {
            dirs = dirPath.split(",");
        }

        Set<String> unDoneDiskFiles = new HashSet<String>();
        for (String dir : dirs) {
            //System.out.print(dir);
            unDoneDiskFiles = FileUtil.traverseFolder(dir);
        }
        for (String filePtah : unDoneDiskFiles) {
            String begintime = "";
            try {
                ESDataFormate esData = FileUtil.fileToBytes(filePtah);
                Long nowTime = System.currentTimeMillis();
                String format = "yyyy-MM-dd-HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                begintime = sdf.format(new Date(Long.valueOf(nowTime)));

                //文件写入ES，文件名后会增加时间戳+.done的后缀。
                String fileFormat = FileUtil.getFileFormat(esData.getName());
                String uuid = CommonUtil.getStringUUID();
                ESUtil.writeToEs(esData, uuid);
                //FileUtil.fileRename(filePtah, begintime + FileUtil.fileSuffixDone);
            } catch (Exception e) {
                log.error("", e);
                //FileUtil.fileRename(filePtah, begintime + FileUtil.fileSuffixFail);
            }
        }
    }

}
