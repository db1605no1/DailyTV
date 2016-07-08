package com.example.dailytv.beans;

import java.util.List;

/**
 * Created by Administrator on 16-7-6.
 */
public class Store{
    private String imgUrl;
    private String text;
    private List<Program> list;

    public Store(String imgUrl, String text, List<Program> list){
        this.imgUrl = imgUrl;
        this.text = text;
        this.list = list;
    }

    public Store(String text, List<Program> list){
        this.text = text;
        this.list = list;
    }

    public List<Program> getList(){
        return list;
    }

    public void setList(List<Program> list){
        this.list = list;
    }

    public Store(){
    }

    public Store(String imgUrl, String text){
        this.imgUrl = imgUrl;
        this.text = text;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
