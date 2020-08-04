package com.transwarp.eswriter.main;
import com.beust.jcommander.JCommander;
import com.transwarp.eswriter.es.FileToEs;
import com.transwarp.eswriter.es.DiskToEs;
import com.transwarp.eswriter.util.GetProperties;
import org.apache.log4j.Logger;

import java.io.File;

public class Main {
    private static Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        log.info("开始读取配置文件conf中的信息！");
        //GetProperties.showConf();

        log.info("开始读取启动参数配置信息！");
        InputParameter inputParameter = new InputParameter();
        JCommander jCommander = JCommander.newBuilder().addObject(inputParameter).build();
        jCommander.parse(args);


        log.info("读取配置信息" + args.toString() + "成功！");

        //判断启动参数中的值
        if (inputParameter.getINPUT_PATH()!=null && inputParameter.getINPUT_PATH().length()!=0 )
            GetProperties.INPUT_PATH=inputParameter.getINPUT_PATH();

        if (inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH()!=null && inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH().length()!=0 )
        GetProperties.DISK_ELASTICSEARCH_SCAN_PATH=inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH();

        if (String.valueOf(inputParameter.getELASTICSEARCH_API_PORT())!=null )
            GetProperties.ELASTICSEARCH_API_PORT=inputParameter.getELASTICSEARCH_API_PORT();

        if (inputParameter.getELASTICSEARCH_CLUSTER_NAME()!=null && inputParameter.getELASTICSEARCH_CLUSTER_NAME().length()!=0 )
            GetProperties.ELASTICSEARCH_CLUSTER_NAME=inputParameter.getELASTICSEARCH_CLUSTER_NAME();

        if (inputParameter.getELASTICSEARCH_INDEX_NAME()!=null && inputParameter.getELASTICSEARCH_INDEX_NAME().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_NAME=inputParameter.getELASTICSEARCH_INDEX_NAME();

        if (inputParameter.getELASTICSEARCH_INDEX_TYPE()!=null && inputParameter.getELASTICSEARCH_INDEX_TYPE().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_TYPE=inputParameter.getELASTICSEARCH_INDEX_TYPE();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_FILENAME()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_FILENAME().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_FILENAME=inputParameter.getELASTICSEARCH_INDEX_FIELD_FILENAME();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_FILEPATH()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_FILEPATH().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_FILEPATH=inputParameter.getELASTICSEARCH_INDEX_FIELD_FILEPATH();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE=inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILID()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILID().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_MAILID=inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILID();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILLEVEL()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILLEVEL().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_MAILLEVEL=inputParameter.getELASTICSEARCH_INDEX_FIELD_MAILLEVEL();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_LOADTIME()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_LOADTIME().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_LOADTIME=inputParameter.getELASTICSEARCH_INDEX_FIELD_LOADTIME();

        if (inputParameter.getELASTICSEARCH_INDEX_FIELD_NODEID()!=null && inputParameter.getELASTICSEARCH_INDEX_FIELD_NODEID().length()!=0 )
            GetProperties.ELASTICSEARCH_INDEX_FIELD_NODEID=inputParameter.getELASTICSEARCH_INDEX_FIELD_NODEID();

        if (inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH()!=null && inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH().length()!=0 )
            GetProperties.DISK_ELASTICSEARCH_SCAN_PATH=inputParameter.getDISK_ELASTICSEARCH_SCAN_PATH();

        if (inputParameter.getFILE_FROMAT_SUPPORT()!=null && inputParameter.getFILE_FROMAT_SUPPORT().length()!=0 )
            GetProperties.FILE_FROMAT_SUPPORT=inputParameter.getFILE_FROMAT_SUPPORT();

        if (inputParameter.getKEYTAB()!=null && inputParameter.getKEYTAB().length()!=0 )
            GetProperties.KEYTAB=inputParameter.getKEYTAB();
        if (inputParameter.getPRINCIPAL()!=null && inputParameter.getPRINCIPAL().length()!=0 )
            GetProperties.PRINCIPAL=inputParameter.getPRINCIPAL();
        if (inputParameter.getSECURITY()!=null && inputParameter.getSECURITY().length()!=0 )
            GetProperties.SECURITY=Boolean.parseBoolean(inputParameter.getSECURITY());

        log.info("配置信息结果如下：");
        GetProperties.showConf();

        if(GetProperties.INPUT_PATH==null || GetProperties.INPUT_PATH.length()==0){
            log.info("请输入需要上传的文件！！！");
            return ;
        }

        //判断输入文件
        File file = new File(GetProperties.INPUT_PATH);
        if (file.exists()) {
            if (file.isDirectory()){
                DiskToEs.writeToEs(GetProperties.INPUT_PATH);
            }else{
                FileToEs.writeToEs(GetProperties.INPUT_PATH);
            }
        }else {
            log.info("上传的文件不存在或者不符合规范，请检查相关路径！！！");
        }

//        String dirPath = "data/";
//        DiskToEs.writeToEs(dirPath);
//
//        String path = "data/test2.pdf";
//        String path1 = "data/MPP.pptx";
//        String path2 = "data/rule.xlsx";
          String path3 = "file.txt";
//
//        FileToEs.writeToEs(path);
//        FileToEs.writeToEs(path1);
//        FileToEs.writeToEs(path2);
//        FileToEs.writeToEs(path3);


    }
}
