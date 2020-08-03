package com.transwarp.eswriter.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class GetProperties {

    //public static final String CONF_PTAH="../conf/";
    public static final String CONF_PTAH="./conf/";
    //private static final String PROPERTIES_NAME = "../conf/conf.properties";
    private static final String PROPERTIES_NAME = CONF_PTAH + "conf.properties";
    private static Logger log = Logger.getLogger(GetProperties.class);

    public static String ELASTICSEARCH_IP = null;
    public static int ELASTICSEARCH_API_PORT = 9300;
    public static String ELASTICSEARCH_CLUSTER_NAME = null;
    public static String ELASTICSEARCH_INDEX_NAME = null;
    public static String ELASTICSEARCH_INDEX_TYPE = null;
    public static String ELASTICSEARCH_INDEX_FIELD_FILENAME = null;
    public static String ELASTICSEARCH_INDEX_FIELD_FILEPATH = null;
    public static String ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE = null;
    public static String ELASTICSEARCH_INDEX_FIELD_MAILID = null;
    public static String ELASTICSEARCH_INDEX_FIELD_MAILLEVEL = null;
    public static String ELASTICSEARCH_INDEX_FIELD_LOADTIME = null;
    public static String ELASTICSEARCH_INDEX_FIELD_NODEID = null;

    public static String DISK_ELASTICSEARCH_SCAN_PATH = null;

    public static String FILE_FROMAT_SUPPORT = null;

    static {
        FileInputStream in = null;
        try{
            Properties properties = new Properties();
            in = new FileInputStream(PROPERTIES_NAME);
            properties.load(in);
            ELASTICSEARCH_IP = properties.getProperty("elasticsearch.ip");
            ELASTICSEARCH_API_PORT = Integer.parseInt(properties.getProperty("elasticsearch.api.port"));
            ELASTICSEARCH_CLUSTER_NAME = properties.getProperty("elasticsearch.cluster.name");
            ELASTICSEARCH_INDEX_NAME = properties.getProperty("elasticsearch.index.name");
            ELASTICSEARCH_INDEX_TYPE = properties.getProperty("elasticsearch.index.type");
            ELASTICSEARCH_INDEX_FIELD_FILENAME = properties.getProperty("elasticsearch.index.field.filename");
            ELASTICSEARCH_INDEX_FIELD_FILEPATH = properties.getProperty("elasticsearch.index.field.filepath");
            ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE = properties.getProperty("elasticsearch.index.field.defaultlanguage");
            ELASTICSEARCH_INDEX_FIELD_MAILID = properties.getProperty("elasticsearch.index.field.mailid");
            ELASTICSEARCH_INDEX_FIELD_MAILLEVEL = properties.getProperty("elasticsearch.index.field.maillevel");
            ELASTICSEARCH_INDEX_FIELD_LOADTIME = properties.getProperty("elasticsearch.index.field.loadtime");
            ELASTICSEARCH_INDEX_FIELD_NODEID = properties.getProperty("elasticsearch.index.field.nodeid");

            DISK_ELASTICSEARCH_SCAN_PATH = properties.getProperty("disk.elasticsearch.scan.path");

            FILE_FROMAT_SUPPORT = properties.getProperty("file.format.support");
            showConf();
            log.info("读取配置信息" + PROPERTIES_NAME + "成功！");
        }catch(Exception e){
            e.printStackTrace();
            log.error("读取配置信息" + PROPERTIES_NAME + "失败！");
        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showConf(){
        log.info("-----------------------配置信息-----------------");

        log.info("ELASTICSEARCH_IP: "+ELASTICSEARCH_IP);
        log.info("ELASTICSEARCH_API_PORT: "+ELASTICSEARCH_API_PORT);
        log.info("ELASTICSEARCH_CLUSTER_NAME: "+ELASTICSEARCH_CLUSTER_NAME);
        log.info("ELASTICSEARCH_INDEX_NAME: "+ELASTICSEARCH_IP);
        log.info("ELASTICSEARCH_INDEX_TYPE: "+ELASTICSEARCH_INDEX_TYPE);
        log.info("ELASTICSEARCH_INDEX_FIELD_FILENAME: "+ELASTICSEARCH_INDEX_FIELD_FILENAME);
        log.info("ELASTICSEARCH_INDEX_FIELD_FILEPATH: "+ELASTICSEARCH_INDEX_FIELD_FILEPATH);
        log.info("ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE: "+ ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE);
        log.info("ELASTICSEARCH_INDEX_FIELD_MAILID: "+ELASTICSEARCH_INDEX_FIELD_MAILID);
        log.info("ELASTICSEARCH_INDEX_FIELD_MAILLEVEL: "+ELASTICSEARCH_INDEX_FIELD_MAILLEVEL);

        log.info("DISK_ELASTICSEARCH_SCAN_PATH: "+DISK_ELASTICSEARCH_SCAN_PATH);

        log.info("FILE_FROMAT_SUPPORT: "+FILE_FROMAT_SUPPORT);
        log.info("----------------------------------------------");
    }

    public static void main(String[] args){
    }
}
