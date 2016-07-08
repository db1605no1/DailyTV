package com.example.dailytv;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.Bind;
import tcking.github.com.giraffeplayer.GiraffePlayer;
import tcking.github.com.giraffeplayer.GiraffePlayerActivity;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class PlayActivity  extends MyActivity{


    @Bind(R.id.play_start)
    Button playStart;
    @Bind(R.id.play_stop)
    Button playStop;
    @Bind(R.id.play_collect)
    Button  playCollect;
    @Bind(R.id.play_share)
    Button  playShare;


    private GiraffePlayer giraffePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initData();
        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();

        String url=bundle.getString("url");
        String tvNmae=bundle.getString("tvName");
        giraffePlayer = new GiraffePlayer(this);
        GiraffePlayerActivity.configPlayer(this).setTitle(url).play(url);



    }

    private void initData(){



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        if (giraffePlayer != null) {
            giraffePlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed(){
        if (giraffePlayer != null && giraffePlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();



    }



    @Override
    protected void onPause() {
        super.onPause();
        if (giraffePlayer != null) {
            giraffePlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (giraffePlayer != null) {
            giraffePlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (giraffePlayer != null) {
            giraffePlayer.onDestroy();
        }
    }

}
