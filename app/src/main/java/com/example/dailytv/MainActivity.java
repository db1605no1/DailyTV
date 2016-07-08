package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.dailytv.adapter.MainVPAdapter;
import com.example.dailytv.fragments.FindFragment;
import com.example.dailytv.fragments.FirstFragment;
import com.example.dailytv.fragments.PlayFragment;
import com.example.dailytv.fragments.StoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MyActivity  {

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
    private Button  headerLogin;


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

    }

    private void initView(){
        View view = navigation.getHeaderView(0);
        headerLogin = (Button) view.findViewById(R.id.header_login);

    }

    //给NavigationView菜单添加点击事件
    private void initMenuListener(){
        Menu menu = navigation.getMenu();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                int id = item.getItemId();
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

        //设置tabLayout和mainViewPager的联动
   mainTab.setupWithViewPager(mainViewPager);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu){

       getMenuInflater().inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    public  void login(View view){
        Intent  intent=new Intent(this,LoginActivity.class);
        this.startActivityForResult(intent,1);



    }
public   void  myAccount(View view){



}




    @Override
    protected void onDestroy(){
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch (resultCode) {
            case RESULT_OK:

                Bundle b=data.getExtras(); //data为回传的Intent
                String name = b.getString("name");
                headerLogin.setText(name);
                Log.e("MainActivity", name);

                break;
            default:
                break;
        }


    }
}
