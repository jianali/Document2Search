package com.transwarp.eswriter.es;

import java.io.Serializable;

public class ESDataFormate implements Serializable{
    private String name;
    private byte[] content;
    private String language;
    private String path;

    public ESDataFormate() {
    }

    public ESDataFormate(String name, byte[] content, String language, String path){
        this.name = name;
        this.content = content;
        this.language = language;
        this.path = path;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public byte[] getContent(){
        return content;
    }

    public void setContent(byte[] content){
        this.content = content;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }
}
