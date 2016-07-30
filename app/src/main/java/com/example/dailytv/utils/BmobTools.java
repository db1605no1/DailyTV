package com.example.dailytv.utils;

import android.content.Context;

import com.example.dailytv.beans.User;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 荒原中的歌声 on 2016/7/9.
 */
public class BmobTools{


   public  static void update(final Context context, User user, String objectId){
        user.update(context,objectId, new UpdateListener(){
            @Override
            public void onSuccess(){
                //Toast.makeText(context, toast1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s){
               // Toast.makeText(context, toast2, Toast.LENGTH_SHORT).show();

            }
        });
    }




}
