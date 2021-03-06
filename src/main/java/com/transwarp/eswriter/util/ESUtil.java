package com.transwarp.eswriter.util;

import com.transwarp.eswriter.es.ESDataFormate;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import transwarp.org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import transwarp.org.elasticsearch.action.index.IndexResponse;
import transwarp.org.elasticsearch.action.search.SearchResponse;
import transwarp.org.elasticsearch.action.update.UpdateRequest;
import transwarp.org.elasticsearch.action.update.UpdateResponse;
import transwarp.org.elasticsearch.client.transport.TransportClient;
import transwarp.org.elasticsearch.common.settings.Settings;
import transwarp.org.elasticsearch.common.transport.InetSocketTransportAddress;
import transwarp.org.elasticsearch.common.xcontent.XContentBuilder;
import transwarp.org.elasticsearch.common.xcontent.XContentFactory;
import transwarp.org.elasticsearch.index.query.BoolQueryBuilder;
import transwarp.org.elasticsearch.index.query.MatchQueryBuilder;
import transwarp.org.elasticsearch.index.query.QueryBuilders;
import transwarp.org.elasticsearch.index.reindex.DeleteByQueryAction;
import transwarp.org.elasticsearch.search.SearchHit;
import transwarp.org.elasticsearch.search.SearchHits;
import transwarp.org.elasticsearch.transport.client.PreBuiltTransportClient;
import transwarp.org.elasticsearch.plugins.Plugin;
import io.transwarp.plugin.doorkeeper.client.DoorKeeperClientPlugin;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeys;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static transwarp.org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class ESUtil {

    private static Logger log = Logger.getLogger(ESUtil.class);
    static final String indexName = GetProperties.ELASTICSEARCH_INDEX_NAME;
    static final String indexType = GetProperties.ELASTICSEARCH_INDEX_TYPE;
    static final String CLUSTER_NAME = GetProperties.ELASTICSEARCH_CLUSTER_NAME;
    static final String ESIP = GetProperties.ELASTICSEARCH_IP;
    static final int PORT = GetProperties.ELASTICSEARCH_API_PORT;
    static final String FILE_NAME = GetProperties.ELASTICSEARCH_INDEX_FIELD_FILENAME;
    static final String FILE_PATH = GetProperties.ELASTICSEARCH_INDEX_FIELD_FILEPATH;
    static final String MAIL_ID = GetProperties.ELASTICSEARCH_INDEX_FIELD_MAILID;
    static final String MAIL_LEVEL = GetProperties.ELASTICSEARCH_INDEX_FIELD_MAILLEVEL;
    static final String LOAD_TIME = GetProperties.ELASTICSEARCH_INDEX_FIELD_LOADTIME;
    static final String NODE_ID = GetProperties.ELASTICSEARCH_INDEX_FIELD_NODEID;
    static final boolean SECURITY = GetProperties.SECURITY;
    static final String SENDER = "sender";
    static final String SENDERADDRESS = "senderaddress";
    static final String RECIPIENT = "recipient";
    static final String RECIPIENTADDRESS = "recipientaddress";

    private static TransportClient client;
    private static Settings settings;

    /**
     * 1. 获得⽤户的keytab；
     * 2. 在客户端初始化的时候执⾏下⾯的代码
     *
     * @param principal
     * @param keytabPath
     * @throws IOException
     */

    private static void initSecurityContext(String principal, String keytabPath)
            throws IOException {
        Configuration cnf = new Configuration();
        cnf.set(CommonConfigurationKeys.HADOOP_SECURITY_AUTHENTICATION,
                "kerberos");
        UserGroupInformation.setConfiguration(cnf);
        UserGroupInformation.loginUserFromKeytab(principal, keytabPath);
    }


    static {

          String principal = GetProperties.PRINCIPAL;
          String keytabPath = GetProperties.KEYTAB;
          boolean isopenSecurity = GetProperties.SECURITY;
//        String principal = "elasticsearch/linu-4-29@TDH";
//        String keytabPath = "/etc/search1/instancegroup1/conf/search.keytab";

        try {
            if(isopenSecurity) {
                settings = Settings.builder()
                        .put("cluster.name", CLUSTER_NAME)
                        .put("security.enable",SECURITY)
                        .put("transport.type","security-netty3")
                        .build();
                initSecurityContext(principal,keytabPath);
            }else{
                settings = Settings.builder()
                        .put("cluster.name", CLUSTER_NAME)
                        .build();
            }
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESIP), PORT));
            client = new PreBuiltTransportClient(settings,
                    Collections.<Class<? extends Plugin>>singletonList(DoorKeeperClientPlugin.class))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESIP), PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized TransportClient getTransportClient() {
        return client;
    }


    //普通文件写入es。写入内容包含两个字段：文件名，文件内容
    public static IndexResponse writeToEs(ESDataFormate esData, String uuid) throws IOException, TikaException, SAXException {
        Long time = System.currentTimeMillis();
        String timeStr = time.toString();
        String indexId = uuid;
        String tempId = "";
        String nodeId = "";

        //将不同格式文件内容由btye[]类型通过tika转换成string。
        String content = TikaUtil.fileToString(esData.getContent());

        XContentBuilder xContentBuilder = jsonBuilder().startObject()
                .field(FILE_NAME, esData.getName())
                //暂时通过文件语言映射表获取文件语言
                //.field(GetLanguage.getLanguageAuto(content),content)
                .field("content", content)
                .field(FILE_PATH, esData.getPath())
                .field(LOAD_TIME, timeStr)
                .field(NODE_ID, nodeId)
                .endObject();
        IndexResponse indexResponse = ESUtil.getTransportClient().prepareIndex(indexName, indexType, indexId)
                .setSource(xContentBuilder)
                .execute()
                .actionGet();
        log.info(esData.getName() + " wrote result: " + indexResponse.toString());
        return indexResponse;
        //client.close();
    }

    public static IndexResponse writeImportToEs(ESDataFormate esData, String uuid, String nodeId) throws IOException, TikaException, SAXException {
        Long time = System.currentTimeMillis();
        String timeStr = time.toString();
        String indexId = uuid;
        String tempId = "";

        //将不同格式文件内容由btye[]类型通过tika转换成string。
        String content = TikaUtil.fileToString(esData.getContent());

        XContentBuilder xContentBuilder = jsonBuilder().startObject()
                .field(FILE_NAME, esData.getName())
                //暂时通过文件语言映射表获取文件语言
                //.field(GetLanguage.getLanguageAuto(content),content)
                .field("content", content)
                .field(FILE_PATH, esData.getPath())
                .field(LOAD_TIME, timeStr)
                .field(NODE_ID, nodeId)
                .endObject();
        IndexResponse indexResponse = ESUtil.getTransportClient().prepareIndex(indexName, indexType, indexId)
                .setSource(xContentBuilder)
                .execute()
                .actionGet();
        log.info(esData.getName() + " wrote result: " + indexResponse.toString());
        return indexResponse;
        //client.close();
    }

    public static Map<String, ArrayList<String>> getFileListBySearchKeyWord(String keyWord, List<String> nodeIdList) {

        TransportClient client = ESUtil.getTransportClient();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termsQuery("nodeid", nodeIdList)).
                must(QueryBuilders.matchQuery("content", keyWord));
        SearchResponse response = client.prepareSearch(indexName).setQuery(boolQuery).setSize(nodeIdList.size()).get();

        Map<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            result.put(hit.getSource().get("nodeid").toString(), new ArrayList());
        }
        for (SearchHit hit : hits) {
            result.get(hit.getSource().get("nodeid").toString()).add(hit.getId());
        }
        return result;
    }

    public static List<String> getEsFileIdByNodeId(String nodeId) {

        List<String> list = new ArrayList<String>();
        TransportClient client = ESUtil.getTransportClient();
        MatchQueryBuilder builder = QueryBuilders.matchQuery("nodeid", nodeId);
        SearchResponse response = client.prepareSearch(indexName).setQuery(builder).get();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            list.add(hit.getId());
        }
        return list;
    }

    public static Boolean updateEsData(String uuid, String value) {

        TransportClient client = ESUtil.getTransportClient();
        UpdateRequest request = new UpdateRequest();
        UpdateResponse response = new UpdateResponse();
        try {
            request.index(indexName).type(indexType).id(uuid).doc(
                    XContentFactory.jsonBuilder().startObject().field("nodeid", value).endObject());
            response = client.update(request).get();
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
        if (response.status().toString().equals("OK")) {
            return true;
        } else {
            return false;
        }

    }

    public static void deleteEsDataByFileId(String esFileId) {

        TransportClient client = ESUtil.getTransportClient();

        client.prepareDelete(indexName, indexType, esFileId).get();

    }

    public static Long deleteAllEsData(String nodeId) {

        TransportClient client = ESUtil.getTransportClient();

        BulkByScrollResponse deleteResponse = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("nodeid", nodeId))
                .source(indexName)
                .get();
        long count = deleteResponse.getDeleted();
        return count;
    }

    public static void main(String[] args) {

    }
}
