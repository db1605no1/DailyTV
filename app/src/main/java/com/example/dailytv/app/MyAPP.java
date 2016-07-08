package com.example.dailytv.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by 荒原中的歌声 on 2016/7/7.
 */
public class MyAPP extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Bmob.initialize(this,"1c4cb71659d3bf083645bb1e7b8c68ec");
    }



}
