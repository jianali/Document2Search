package com.transwarp.util;

import java.util.Date;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/59:37
 **/
public class DateUtil {
    public static long getSecondsBetween(Date startTime, Date endTime) {
        long seconds = 0L;
        try {
            long msStart = startTime.getTime();
            long msEnd = endTime.getTime();
            seconds = ( msEnd-msStart ) / 1000 ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seconds;
    }
}
