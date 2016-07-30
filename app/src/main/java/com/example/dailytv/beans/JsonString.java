package com.example.dailytv.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by 荒原中的歌声 on 2016/7/7.
 */
public class JsonString extends BmobObject{

    private   String classify;
    private  String  json;

    public String getClassify(){
        return classify;
    }

    public void setClassify(String classify){
        this.classify = classify;
    }

    public String getJson(){
        return json;
    }

    public void setJson(String json){
        this.json = json;
    }

    public JsonString(String classify, String json){
        this.classify = classify;
        this.json = json;
    }

}
