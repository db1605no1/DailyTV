package com.example.dailytv.beans;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class Program{

    private int  iconId;
    private  String name;
    private String url;

    public Program(String url, String name, int iconId){
        this.url = url;
        this.name = name;
        this.iconId = iconId;
    }

    public int getIconId(){
        return iconId;
    }

    public void setIconId(int iconId){
        this.iconId = iconId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
