package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import tcking.github.com.giraffeplayer.GiraffePlayer;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class PlayActivity  extends MyActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paly);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        GiraffePlayer giraffePlayer=new GiraffePlayer(this);
        giraffePlayer.play(url);
    }

}
