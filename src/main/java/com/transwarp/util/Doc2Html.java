package com.transwarp.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * @Author zhouchaolong
 * @Description
 * @Date 2020/3/413:46
 **/
public class Doc2Html {

    public static String WordToHtml(InputStream input,String htmlPathDir,String htmlPath,String fileType) throws IOException, TransformerException,
            ParserConfigurationException{
        if(".doc".equals(fileType)){
            return Word2003ToHtml(input,htmlPathDir,htmlPath);
        }else if(".docx".equals(fileType)){
            return Word2007ToHtml(input,htmlPathDir,htmlPath);
        }
        return null;
    }

    public static String Word2003ToHtml(InputStream input,String htmlPathDir,String htmlPath) throws IOException, TransformerException,
            ParserConfigurationException {
        final String imagePath = htmlPathDir + "image" + File.separator;

        // 判断html文件是否存在，每次重新生成
        File htmlFile = new File(htmlPath);
        // 1) 加载word文档生成 XWPFDocument对象
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());

        // 设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagePath);
                if (!imgPath.exists()) {// 图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(imagePath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 图片在html文件上的路径 相对路径
                return "image/" + suggestedName;
            }
        });

        // 解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

        // 生成html文件上级文件夹
        File folder = new File(htmlPathDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 生成html文件地址
        OutputStream outStream = new FileOutputStream(htmlFile);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);

        outStream.close();

        return htmlFile.getAbsolutePath();
    }

    public static String Word2007ToHtml(InputStream input,String htmlPathDir,String htmlPath)
            throws IOException {
        final String imagePath = htmlPathDir + "image" + File.separator;

        // 判断html文件是否存在，每次重新生成
        File htmlFile = new File(htmlPath);

        // 1) 加载word文档生成 XWPFDocument对象
        XWPFDocument document = new XWPFDocument(input);

        // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imgFolder = new File(imagePath);
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(imgFolder));
        // html中图片的路径 相对路径
        options.URIResolver(new BasicURIResolver("image"));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);

        // 3) 将 XWPFDocument转换成XHTML
        // 生成html文件上级文件夹
        File folder = new File(htmlPathDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        OutputStream out = new FileOutputStream(htmlFile);
        XHTMLConverter.getInstance().convert(document, out, options);

        return htmlFile.getAbsolutePath();
    }
}

