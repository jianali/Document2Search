package com.transwarp.util;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/2410:57
 **/
public final class BytesHelper {

    private BytesHelper() {
    }

    public static int toInt(byte[] bytes) {
        int result = 0;
        for ( int i = 0; i < 4; i++ ) {
            result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }
}
