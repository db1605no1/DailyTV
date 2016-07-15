package com.example.dailytv;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.dailytv.beans.HisProgram;
import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.User;
import com.example.dailytv.utils.BmobTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import tcking.github.com.giraffeplayer.GiraffePlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class PlayActivity extends AppCompatActivity{

    @Bind(R.id.play_start)
    CheckBox playStart;
    @Bind(R.id.play_collect)
    CheckBox playCollect;
    @Bind(R.id.play_share)
    Button playShare;

    private GiraffePlayer giraffePlayer;
    private String url;
    private String tvNmae;
    private Boolean shared = false;
    private String name;
    private List<Program> collects;
    private String objectID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        //得到视频地址和名称
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");
        tvNmae = bundle.getString("tvName");
        //储存到播放历史
        saveHistory();
        initData();
        giraffePlayer = new GiraffePlayer(this);
        giraffePlayer.play(url);
        giraffePlayer.setTitle(url);
        playStart.setChecked(true);
        giraffePlayer.onComplete(new Runnable(){
            @Override
            public void run(){
                //callback when video is finish
                Toast.makeText(getApplicationContext(), "video play completed", Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new GiraffePlayer.OnInfoListener(){
            @Override
            public void onInfo(int what, int extra){
                switch(what){
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //do something when buffering start
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //do something when buffering end
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //download speed
                        //  ((TextView) findViewById(R.id.tv_speed)).setText(Formatter.formatFileSize(getApplicationContext(), extra) + "/s");
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //do something when video rendering
                        // findViewById(R.id.tv_speed).setVisibility(View.GONE);
                        break;
                }
            }
        }).onError(new GiraffePlayer.OnErrorListener(){
            @Override
            public void onError(int what, int extra){
                Toast.makeText(getApplicationContext(), "video play error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveHistory(){
        //储存到播放历史
        HisProgram program = new HisProgram(tvNmae, url);
        if(MainActivity.history.contains(program)){
            MainActivity.history.remove(program);
        }else if(MainActivity.history.size() > 51){
            MainActivity.history.remove(0);
        }
        MainActivity.history.add(program);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if(giraffePlayer != null){
            giraffePlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed(){
        if(giraffePlayer != null && giraffePlayer.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(giraffePlayer != null){
            giraffePlayer.onPause();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(giraffePlayer != null){
            giraffePlayer.onPause();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(giraffePlayer != null){
            giraffePlayer.onResume();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(giraffePlayer != null){
            giraffePlayer.onDestroy();
        }
        //更新收藏数据到Bmob端
        User user0 = new User();
        user0.setValue("collects", collects);
        BmobTools.update(this, user0, objectID);
        ButterKnife.unbind(this);

    }

    private void initData(){
        //首先判断是否登录
        name = MainActivity.headerLogin.getText().toString();
        if(!name.equals("点击此处登录")){
            //若用户已登录，在Bmob端查询是否已经收藏该节目，默认未收藏，通过shared变量标记
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("name", name);
            query.findObjects(this, new FindListener<User>(){
                @Override
                public void onSuccess(List<User> list){
                    if(list.size() == 1){
                        User user = list.get(0);
                        collects = user.getCollects();
                        objectID = user.getObjectId();
                        if(collects.size() != 0){
                            List<String> collectNames = new ArrayList<String>();
                            for(Program program : collects){
                                collectNames.add(program.getTVName());
                            }
                            //已收藏
                            if(collectNames.contains(tvNmae)){
                                shared = true;
                                playCollect.setChecked(true);
                            }
                        }
                    }
                }

                @Override
                public void onError(int i, String s){
                    Toast.makeText(PlayActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void play_collect(View view){
        if(name.equals("点击此处登录")){
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
            playCollect.setChecked(false);
        }else{
            Log.e("PlayActivity", "hhhhhhhhhhhhh");
            Program program = new Program(tvNmae, url);
            if(shared == true){
                collects.remove(program);
                Log.e("PlayActivity", "collects.size():" + collects.size());
                playCollect.setChecked(false);
                shared = false;
                Toast.makeText(this, "已经取消收藏", Toast.LENGTH_SHORT).show();
            }else{
                playCollect.setChecked(true);
                collects.add(program);
                Log.e("PlayActivity", "collects.size():" + collects.size());
                shared = true;
                Toast.makeText(this, "收藏成功！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void playPlay(View view){
        if(playStart.isChecked()){
            giraffePlayer.start();
        }else{
            giraffePlayer.pause();
        }
    }

    public void share(View view){
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     //   oks.setTitle("仅供测试");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
      //  oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
      //  oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
       // oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
     //   oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }



}
