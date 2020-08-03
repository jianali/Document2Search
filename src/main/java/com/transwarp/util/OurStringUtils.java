package com.transwarp.util;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OurStringUtils {
    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    /**
     * 从cookie中拿token 公用方法
     * @param str
     * @return
     */
    public static Map cookieParse(String str) {
        Map<String, String> map = new HashMap<>();
        if(Objects.isNull(str) || str.isEmpty()) return null;
        Arrays.stream(str.split(";"))
                .filter(kv -> kv.contains("="))
                .map(kv -> kv.split("="))
                .forEach(array -> map.put(array[0].trim(), array[1]));
        return map;
    }


    /**
     *功能描述 字符串长度不足则用0补足
     * @Author: zhouchaolong
     * @Date: 2020/3/4 20:31
     * @params: [str, strLength]
     * @return: java.lang.String
     **/
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                //sb.append("0").append(str);// 左补0
                 sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
}
