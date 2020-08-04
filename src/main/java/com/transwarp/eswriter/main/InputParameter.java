package com.transwarp.eswriter.main;
import com.beust.jcommander.Parameter;
import org.apache.log4j.Logger;

public class InputParameter {

    private static Logger log = Logger.getLogger(InputParameter.class);

    //可以启动过程中动态加载的组件
    //需要上传文件的路径
    @Parameter(names = {"-INPUT_PATH"},description = "the path or file to push elasticsearch")
    private String INPUT_PATH;
    @Parameter(names = {"-ELASTICSEARCH_IP"},description = "")
    private  String ELASTICSEARCH_IP = null;
    @Parameter(names = {"-ELASTICSEARCH_API_PORT"},description = "")
    private  int ELASTICSEARCH_API_PORT = 9300;
    @Parameter(names = {"-ELASTICSEARCH_CLUSTER_NAME"},description = "")
    private  String ELASTICSEARCH_CLUSTER_NAME = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_NAME"},description = "")
    private  String ELASTICSEARCH_INDEX_NAME = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_TYPE"},description = "")
    private  String ELASTICSEARCH_INDEX_TYPE = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_FILENAME"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_FILENAME = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_FILEPATH"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_FILEPATH = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_MAILID"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_MAILID = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_MAILLEVEL"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_MAILLEVEL = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_LOADTIME"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_LOADTIME = null;
    @Parameter(names = {"-ELASTICSEARCH_INDEX_FIELD_NODEID"},description = "")
    private  String ELASTICSEARCH_INDEX_FIELD_NODEID = null;
    @Parameter(names = {"-DISK_ELASTICSEARCH_SCAN_PATH"},description = "")
    private  String DISK_ELASTICSEARCH_SCAN_PATH = null;
    @Parameter(names = {"-FILE_FROMAT_SUPPORT"},description = "")
    private  String FILE_FROMAT_SUPPORT = null;
    @Parameter(names = {"-SECURITY"},description = "")
    private  String SECURITY = null;
    @Parameter(names = {"-PRINCIPAL"},description = "")
    private  String PRINCIPAL = null;
    @Parameter(names = {"-KEYTAB"},description = "")
    private  String KEYTAB = null;

    public String getSECURITY() {
        return SECURITY;
    }

    public void setSECURITY(String SECURITY) {
        this.SECURITY = SECURITY;
    }

    public String getPRINCIPAL() {
        return PRINCIPAL;
    }

    public void setPRINCIPAL(String PRINCIPAL) {
        this.PRINCIPAL = PRINCIPAL;
    }

    public String getKEYTAB() {
        return KEYTAB;
    }

    public void setKEYTAB(String KEYTAB) {
        this.KEYTAB = KEYTAB;
    }



    public String getINPUT_PATH() {
        return INPUT_PATH;
    }

    public void setINPUT_PATH(String INPUT_PATH) {
        this.INPUT_PATH = INPUT_PATH;
    }

    public String getELASTICSEARCH_IP() {
        return ELASTICSEARCH_IP;
    }

    public void setELASTICSEARCH_IP(String ELASTICSEARCH_IP) {
        this.ELASTICSEARCH_IP = ELASTICSEARCH_IP;
    }

    public int getELASTICSEARCH_API_PORT() {
        return ELASTICSEARCH_API_PORT;
    }

    public void setELASTICSEARCH_API_PORT(int ELASTICSEARCH_API_PORT) {
        this.ELASTICSEARCH_API_PORT = ELASTICSEARCH_API_PORT;
    }

    public String getELASTICSEARCH_CLUSTER_NAME() {
        return ELASTICSEARCH_CLUSTER_NAME;
    }

    public void setELASTICSEARCH_CLUSTER_NAME(String ELASTICSEARCH_CLUSTER_NAME) {
        this.ELASTICSEARCH_CLUSTER_NAME = ELASTICSEARCH_CLUSTER_NAME;
    }

    public String getELASTICSEARCH_INDEX_NAME() {
        return ELASTICSEARCH_INDEX_NAME;
    }

    public void setELASTICSEARCH_INDEX_NAME(String ELASTICSEARCH_INDEX_NAME) {
        this.ELASTICSEARCH_INDEX_NAME = ELASTICSEARCH_INDEX_NAME;
    }

    public String getELASTICSEARCH_INDEX_TYPE() {
        return ELASTICSEARCH_INDEX_TYPE;
    }

    public void setELASTICSEARCH_INDEX_TYPE(String ELASTICSEARCH_INDEX_TYPE) {
        this.ELASTICSEARCH_INDEX_TYPE = ELASTICSEARCH_INDEX_TYPE;
    }

    public String getELASTICSEARCH_INDEX_FIELD_FILENAME() {
        return ELASTICSEARCH_INDEX_FIELD_FILENAME;
    }

    public void setELASTICSEARCH_INDEX_FIELD_FILENAME(String ELASTICSEARCH_INDEX_FIELD_FILENAME) {
        this.ELASTICSEARCH_INDEX_FIELD_FILENAME = ELASTICSEARCH_INDEX_FIELD_FILENAME;
    }

    public String getELASTICSEARCH_INDEX_FIELD_FILEPATH() {
        return ELASTICSEARCH_INDEX_FIELD_FILEPATH;
    }

    public void setELASTICSEARCH_INDEX_FIELD_FILEPATH(String ELASTICSEARCH_INDEX_FIELD_FILEPATH) {
        this.ELASTICSEARCH_INDEX_FIELD_FILEPATH = ELASTICSEARCH_INDEX_FIELD_FILEPATH;
    }

    public String getELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE() {
        return ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE;
    }

    public void setELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE(String ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE) {
        this.ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE = ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE;
    }

    public String getELASTICSEARCH_INDEX_FIELD_MAILID() {
        return ELASTICSEARCH_INDEX_FIELD_MAILID;
    }

    public void setELASTICSEARCH_INDEX_FIELD_MAILID(String ELASTICSEARCH_INDEX_FIELD_MAILID) {
        this.ELASTICSEARCH_INDEX_FIELD_MAILID = ELASTICSEARCH_INDEX_FIELD_MAILID;
    }

    public String getELASTICSEARCH_INDEX_FIELD_MAILLEVEL() {
        return ELASTICSEARCH_INDEX_FIELD_MAILLEVEL;
    }

    public void setELASTICSEARCH_INDEX_FIELD_MAILLEVEL(String ELASTICSEARCH_INDEX_FIELD_MAILLEVEL) {
        this.ELASTICSEARCH_INDEX_FIELD_MAILLEVEL = ELASTICSEARCH_INDEX_FIELD_MAILLEVEL;
    }

    public String getELASTICSEARCH_INDEX_FIELD_LOADTIME() {
        return ELASTICSEARCH_INDEX_FIELD_LOADTIME;
    }

    public void setELASTICSEARCH_INDEX_FIELD_LOADTIME(String ELASTICSEARCH_INDEX_FIELD_LOADTIME) {
        this.ELASTICSEARCH_INDEX_FIELD_LOADTIME = ELASTICSEARCH_INDEX_FIELD_LOADTIME;
    }

    public String getELASTICSEARCH_INDEX_FIELD_NODEID() {
        return ELASTICSEARCH_INDEX_FIELD_NODEID;
    }

    public void setELASTICSEARCH_INDEX_FIELD_NODEID(String ELASTICSEARCH_INDEX_FIELD_NODEID) {
        this.ELASTICSEARCH_INDEX_FIELD_NODEID = ELASTICSEARCH_INDEX_FIELD_NODEID;
    }

    public String getDISK_ELASTICSEARCH_SCAN_PATH() {
        return DISK_ELASTICSEARCH_SCAN_PATH;
    }

    public void setDISK_ELASTICSEARCH_SCAN_PATH(String DISK_ELASTICSEARCH_SCAN_PATH) {
        this.DISK_ELASTICSEARCH_SCAN_PATH = DISK_ELASTICSEARCH_SCAN_PATH;
    }

    public String getFILE_FROMAT_SUPPORT() {
        return FILE_FROMAT_SUPPORT;
    }

    public void setFILE_FROMAT_SUPPORT(String FILE_FROMAT_SUPPORT) {
        this.FILE_FROMAT_SUPPORT = FILE_FROMAT_SUPPORT;
    }

    public void showConf(){
        log.info("-----------------------配置信息-----------------");

        log.info("ELASTICSEARCH_IP: "+this.getELASTICSEARCH_IP());
        log.info("ELASTICSEARCH_API_PORT: "+this.getELASTICSEARCH_API_PORT());
        log.info("ELASTICSEARCH_CLUSTER_NAME: "+this.getELASTICSEARCH_CLUSTER_NAME());
        log.info("ELASTICSEARCH_INDEX_NAME: "+this.getELASTICSEARCH_IP());
        log.info("ELASTICSEARCH_INDEX_TYPE: "+this.getELASTICSEARCH_INDEX_TYPE());
        log.info("ELASTICSEARCH_INDEX_FIELD_FILENAME: "+this.getELASTICSEARCH_INDEX_FIELD_FILENAME());
        log.info("ELASTICSEARCH_INDEX_FIELD_FILEPATH: "+this.getELASTICSEARCH_INDEX_FIELD_FILEPATH());
        log.info("ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE: "+ this.getELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE());
        log.info("ELASTICSEARCH_INDEX_FIELD_MAILID: "+this.getELASTICSEARCH_INDEX_FIELD_MAILID());
        log.info("ELASTICSEARCH_INDEX_FIELD_MAILLEVEL: "+this.getELASTICSEARCH_INDEX_FIELD_MAILLEVEL());

        log.info("DISK_ELASTICSEARCH_SCAN_PATH: "+this.getDISK_ELASTICSEARCH_SCAN_PATH());

        log.info("FILE_FROMAT_SUPPORT: "+this.getFILE_FROMAT_SUPPORT());
        log.info("----------------------------------------------");
    }

}
