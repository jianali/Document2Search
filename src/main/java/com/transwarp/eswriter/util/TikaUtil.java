package com.transwarp.eswriter.util;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TikaUtil {

    public static String fileToString(byte[] content) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        //FileInputStream inputstream = new FileInputStream(new File(path));
        //InputStream inputstream = new FileInputStream(new File(path));
        ParseContext pcontext = new ParseContext();
        Parser parser = new AutoDetectParser();
        InputStream inputstream = new ByteArrayInputStream(content);
        parser.parse(inputstream, handler, metadata, pcontext);
        //getting the content of the document
        return handler.toString();
    }
}
