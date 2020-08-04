# 手册
> 本程序主要是将文本数据导入进elasticsearch数据库中。支持的文本格式主要有pdf、pptx、doc、docx、txt等

文件结构：
conf：工具配置文件路径，包含conf.properties和log4j.properties；其中conf.properties为一些默认参数配置，可根据需求修改。log4j.properties为日志配置。
data：需要上传的文档路径，当然你也可以自定义自己的文档路径。
dependency：依赖的jar包。
document2es.sh：工具使用脚本。
Document2Search-1.0-SNAPSHOT.jar：工程主jar包。

使用方法：
方法一：将参数写入conf/conf.properties中，然后通过以下命令启动：
sh document2es.sh --input.path /home/lj/doc2es/data/test.doc
其中/home/lj/doc2es/data/test.doc为需要上传的文件或者文件夹。

方法二：启动命令中指定参数，例如：
sh document2es.sh --input.path /home/lj/doc2es/data --disk.elasticsearch.scan.path "建信金科spark.docx,test2.docx,test.doc" --isopen.security true --keytab /etc/search1/instancegroup1/conf/search.keytab --principal elasticsearch/linu-4-29@TDH
