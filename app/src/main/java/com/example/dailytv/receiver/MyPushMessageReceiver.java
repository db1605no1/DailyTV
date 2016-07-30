package com.example.dailytv.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.dailytv.R;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

/**
 * Created by 荒原中的歌声 on 2016/7/14.
 */

public class MyPushMessageReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {


        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            String msg = intent.getStringExtra("msg");
            JSONObject json=new JSONObject();
            String  str=null;
            try{
               str=json.getString("alert");
            }catch(JSONException e){
                e.printStackTrace();
            }
            Notification notification = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.emoji_52).setContentText(str).setContentTitle("习近平发来贺电").build();
            NotificationManager  manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         manager.notify(0,notification);

        }
    }

}