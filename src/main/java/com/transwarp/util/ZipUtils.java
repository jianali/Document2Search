package com.transwarp.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/1817:54
 **/
public class ZipUtils {

    public static void doZip(InputStream inputStream,String entryName, ZipOutputStream out) throws IOException {
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        int len = 0 ;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        inputStream.close();
    }
}
