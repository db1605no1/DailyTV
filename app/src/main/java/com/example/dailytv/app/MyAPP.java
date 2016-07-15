package com.example.dailytv.app;

import android.app.Application;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created by 荒原中的歌声 on 2016/7/7.
 */
public class MyAPP extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        // 初始化BmobSDK
        Bmob.initialize(this,"1c4cb71659d3bf083645bb1e7b8c68ec");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this);
    }



}
