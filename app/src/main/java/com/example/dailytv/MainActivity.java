package com.example.dailytv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.dailytv.adapter.MainVPAdapter;
import com.example.dailytv.beans.HisProgram;
import com.example.dailytv.fragments.FindFragment;
import com.example.dailytv.fragments.FirstFragment;
import com.example.dailytv.fragments.PlayFragment;
import com.example.dailytv.fragments.StoreFragment;
import com.example.dailytv.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MyActivity{

    @Bind(R.id.main_toolBar)
    Toolbar mainToolBar;
    @Bind(R.id.main_tab)
    TabLayout mainTab;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.main_viewPager)
    ViewPager mainViewPager;

    private List<Fragment> list = new ArrayList<>();
    public static Button headerLogin;
    private ImageView mainIcon;
    private String objectId;
    private Boolean isLogin = false;
    public static List<HisProgram> history;
     public   static int  back =1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initMenuListener();
        //查询播放历史
        queryHistory();
    }

    private void initView(){
        View view = navigation.getHeaderView(0);
        headerLogin = (Button) view.findViewById(R.id.header_login);
        mainIcon = (ImageView) view.findViewById(R.id.main_icon);
    }

    //给NavigationView菜单添加点击事件
    private void initMenuListener(){
        Menu menu = navigation.getMenu();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                int id = item.getItemId();
                switch(id){
                    case R.id.menu_collect:
                        String name = headerLogin.getText().toString();
                        if(name.equals("点击此处登录")){
                            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        break;
                    case R.id.menu_history:
                        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(intent);
                      break;
                    case  R.id.menu_version:
                        Intent intent2 = new Intent(MainActivity.this, VersionActivity.class);
                        startActivity(intent2);

               break;
                }
                return true;
            }
        });
    }

    private void initData(){
        navigation.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolBar, 0, 0);
        toggle.syncState();
        list.add(new FirstFragment());
        list.add(new PlayFragment());
        list.add(new StoreFragment());
        list.add(new FindFragment());
        MainVPAdapter md = new MainVPAdapter(getSupportFragmentManager(), list);
        mainViewPager.setAdapter(md);
        mainViewPager.setOffscreenPageLimit(4);
        //设置tabLayout和mainViewPager的联动
        mainTab.setupWithViewPager(mainViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if(itemId == R.id.menu_history1){
            startActivity(new Intent(this, HistoryActivity.class));
        }else if(itemId == R.id.menu_search){
            startActivity(new Intent(this, SearchActivity.class));
        }
        return true;
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivityForResult(intent, Constant.LOGIN);
    }

    public void myAccount(View view){
        if(isLogin){
            Intent intent = new Intent(this, MyAccountActivity.class);
            intent.putExtra("objectId", objectId);
            startActivityForResult(intent, Constant.MYACCOUNT);
        }else{
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void queryHistory(){
        SharedPreferences tv = getSharedPreferences("dailyTV", MODE_PRIVATE);
        String tvJson = tv.getString("TVJson", "null");
        Log.e("MainActivity", tvJson);
        if(!tvJson.equals("null")){
            history = JSON.parseArray(tvJson, HisProgram.class);
        }else{
            history = new ArrayList<>();
        }
        Log.e("MainActivity", "history.size():" + history.size());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ButterKnife.unbind(this);
        //储存播放历史
        if(history.size() != 0){
            String tvjson = JSON.toJSONString(history);
            SharedPreferences tv = getSharedPreferences("dailyTV", MODE_PRIVATE);
            SharedPreferences.Editor edit = tv.edit();
            edit.putString("TVJson", tvjson);
            edit.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case Constant.LOGIN:
                    isLogin = true;
                    Bundle b = data.getExtras(); //data为回传的Intent
                    String name = b.getString("name");
                    objectId = b.getString("objectId");
                    headerLogin.setText(name);
                    String absolutePath = b.getString("absolutePath");
                    if(absolutePath != null){
                        Bitmap bitmap = BitmapFactory.decodeFile(absolutePath);
                        mainIcon.setImageBitmap(bitmap);
                    }
                    break;
                case Constant.MYACCOUNT:
                    String absolutePath2 = data.getStringExtra("absolutePath");
                    if(absolutePath2 != null){
                        Bitmap bitmap = BitmapFactory.decodeFile(absolutePath2);
                        mainIcon.setImageBitmap(bitmap);
                    }
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed(){
        back++;
   if(back==2){
    Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
       new  Thread(new Runnable(){
           @Override
           public void run(){
               SystemClock.sleep(3000);
               back=1;
           }
       }).start();


   }else if (back>2){
            super.onBackPressed();
        }

    }
}
