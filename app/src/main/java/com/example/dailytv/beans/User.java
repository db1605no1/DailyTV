package com.example.dailytv.beans;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 荒原中的歌声 on 2016/7/7.
 */
public class User  extends BmobObject{
private   String  name;
    private String pwd;
  private    List<Program>  collects;
   private  BmobFile   icon;

    public BmobFile getIcon(){
        return icon;
    }

    public void setIcon(BmobFile icon){
        this.icon = icon;
    }

    public User(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    public User(String name, List<Program> collects, String pwd){
        this.name = name;
        this.collects = collects;
        this.pwd = pwd;
    }

    public List<Program> getCollects(){
        return collects;
    }

    public void setCollects(List<Program> collects){
        this.collects = collects;
    }

    public User(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPwd(){
        return pwd;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }


}
