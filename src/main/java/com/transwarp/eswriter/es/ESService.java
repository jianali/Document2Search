package com.transwarp.eswriter.es;

import com.transwarp.eswriter.util.ESUtil;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ESService {

    private static Logger log = Logger.getLogger(ESService.class);

    public static List<String> getEsFileIdByNodeId(String nodeId) {

        return ESUtil.getEsFileIdByNodeId(nodeId);
    }

    public static void deleteEsFileByNodeId(String nodeId) {
        ESUtil.deleteAllEsData(nodeId);
    }

    public static void deleteEsFileByEsFileId(String esFileId) {
        ESUtil.deleteEsDataByFileId(esFileId);
    }

    public static Boolean updateESNodeIdByEsFileId(String esFileId, String nodeId) {

        return ESUtil.updateEsData(esFileId, nodeId);
    }

    public static Map<String, ArrayList<String>> getFileListBySearchKeyWord(String keyWord, List<String> nodeIdList) throws TikaException, IOException, SAXException {

        return ESUtil.getFileListBySearchKeyWord(keyWord, nodeIdList);
    }
}
