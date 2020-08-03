package com.transwarp.util;

import java.net.InetAddress;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/2410:57
 **/
public abstract class AbstractUUIDGenerator {
    private static final int IP;
    static {
        int ipadd;
        try {
            ipadd = BytesHelper.toInt( InetAddress.getLocalHost().getAddress() );
        }
        catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short) 0;
    private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );

    public  AbstractUUIDGenerator() {
    }

    protected static int getJVM() {
        return JVM;
    }

    protected static short getCount() {
        synchronized(AbstractUUIDGenerator.class) {
            if ( counter < 0 ) {
                counter=0;
            }
            return counter++;
        }
    }

    protected static int getIP() {
        return IP;
    }

    protected static short getHiTime() {
        return (short) ( System.currentTimeMillis() >>> 32 );
    }

    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }
}
