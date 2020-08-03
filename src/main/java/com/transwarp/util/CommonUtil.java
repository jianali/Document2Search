package com.transwarp.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zhouchaolong
 * @Description 通用的工具类
 * @Date 2020/1/2114:24
 **/
public class CommonUtil {
    /**
     *功能描述 获取UUID
     * @Author: zhouchaolong
     * @Date: 2020/1/21 14:24
     * @params: []
     * @return: java.lang.String
     **/
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static  String getStringUUID(){
//        String no = "" ;
//        UUID uuid = UUID.randomUUID();
//        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
//        no = uuid.toString().substring(0, 8);
//        no = no + sdf ;
        return SequentialUuidHexGenerator.generate() ;
    }

    //去除大小写字母o,去除数字0,小写字母l，大小写字母i
    public static String getStringByLength(Integer num){
        String a = "123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ!@#";
        char[] rands = new char[num];
        for (int i = 0; i < rands.length; i++)
        {
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
        }
        return String.valueOf(rands);
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
