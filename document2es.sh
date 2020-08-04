#
# 功能描述：将文本数据写入elasticsearch中
# author by transwarp
# lijian
# 日期：2020/08/03
#

#！/bin/bash

#set -x
set -e
echo "***********************************************"
echo "************welcome to use doc2es**************"
echo "***********************************************"


function print_usage(){
  echo "Tool Describe: This tool can put file or direction into elasticsearch, like .pdf,.doc,.docx,.pdf,.txt,.ppt,.pptx,.xls and so on. the following value can also configured in conf/conf.properties " 
  echo "Usage: doc2es [--config confdir] [--loglevel loglevel] COMMAND"
  echo "       where COMMAND is one of:"
  echo "  input.path                                    the path or file you want to upload those to elasticsearch."
  echo "  elasticsearch.index.field.defaultlanguage     the language of context,default value is ch."
  echo "  elasticsearch.ip                              the elasticsearch ip address"
  echo "  elasticsearch.api.port                        the api port of elasticsearch,default value is 9300"
  echo "  elasticsearch.cluster.name                    the name of cluster name"
  echo "  elasticsearch.index.name                      the elasticsearch index you can specify"
  echo "  elasticsearch.index.type                      the elasticsearch type you can specify"
  echo "  elasticsearch.index.field.filename            the elasticsearch field name"
  echo "  elasticsearch.index.field.filepath            the elasticsearch field name"
  echo "  elasticsearch.index.field.loadtime            the elasticsearch field name"
  echo "  elasticsearch.index.field.nodeid              the elasticsearch field name"
  echo "  disk.elasticsearch.scan.path                  the local data direction"
  echo "  disk.elasticsearch.scan.interval.time         scan interval time"
  echo "  file.format.support                           the support file format,eg:doc,docx,ppt,pptx,pdf,xls,xlsx,txt"
  echo ""
  echo "Most commands print help when invoked w/o parameters."
}

if [ $# = 0 ]; then
  print_usage
  exit
fi

COMMAND=$1

case $COMMAND in
  # usage flags
  --help|-help|-h)
    print_usage
    exit
    ;;
esac


##这是个while循环，判断用[ $lijian == 666 ]这种方式,shift用于参数右移，挨个输出参数
while [[ $# -gt 0 ]] || [[ $# -ne 0 ]]; do
        case $1 in
            -h | --help)
                print_usage
                shift
                ;;
	    --input.path)
                shift
		    DOC2ES_CONF="${DOC2ES_CONF} -INPUT_PATH $1"
		    echo $DOC2ES_CONF
                shift
                ;;
            --elasticsearch.index.field.defaultlanguage)
                shift
		    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_FIELD_DEFAULTLANGUAGE $1"
                    echo $DOC2ES_CONF
                shift
                ;;
            --elasticsearch.ip)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_IP $1"                    
                shift
                ;;
            --elasticsearch.api.port)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_API_PORT $1"
                shift
                ;;
            --elasticsearch.cluster.name)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_CLUSTER_NAME $1"
                shift
                ;;
            --elasticsearch.index.name)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_NAME $1"
                shift
                ;;
            --elasticsearch.index.type)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_TYPE $1"
                shift
                ;;
            --elasticsearch.index.field.filename)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_FIELD_FILENAME $1"
                shift
                ;;

            --elasticsearch.index.field.filepath)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_FIELD_FILEPATH $1"
                shift
                ;;
            --elasticsearch.index.field.loadtime)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_FIELD_LOADTIME $1"
                shift
                ;;
            --elasticsearch.index.field.nodeid)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -ELASTICSEARCH_INDEX_FIELD_NODEID $1"
                shift
                ;;
            --disk.elasticsearch.scan.path)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -DISK_ELASTICSEARCH_SCAN_PATH $1"
                shift
                ;;
            --file.format.support)
                shift
                    DOC2ES_CONF="${DOC2ES_CONF} -FILE_FROMAT_SUPPORT $1"
                shift
                ;;


                      *)
                echo "the input config is error, please check the config!!!"$1
                exit
                shift
        esac
done

exec java -jar Document2Search-1.0-SNAPSHOT-jar-with-dependencies.jar $DOC2ES_CONF
