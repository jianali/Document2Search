package com.transwarp.util;

/**
 *功能描述 有序的UUID
 * @Author: zhouchaolong
 * @Date: 2020/3/24 10:58
 * @params:
 * @return:
 **/
public class SequentialUuidHexGenerator extends AbstractUUIDGenerator{

    private static final String sep = "-";

    public static String generate() {
        String uuid =
                format( getJVM() ) + sep
                        + format( getHiTime() ) + sep
                        + format( getLoTime() ) + sep
                        + format( getIP() ) + sep
                        + format( getCount() );
        return uuid.replaceAll("-","");
    }

    protected static String format(int intValue) {
        String formatted = Integer.toHexString( intValue );
        StringBuilder buf = new StringBuilder( "00000000" );
        buf.replace( 8 - formatted.length(), 8, formatted );
        return buf.toString();
    }

    protected  static String format(short shortValue) {
        String formatted = Integer.toHexString( shortValue );
        StringBuilder buf = new StringBuilder( "0000" );
        buf.replace( 4 - formatted.length(), 4, formatted );
        return buf.toString();
    }
}
