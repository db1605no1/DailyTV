package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dailytv.adapter.ProgramListAdapter;
import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class CollectActivity extends MyActivity{

    @Bind(R.id.collect_listview)
    ListView listView;
    @Bind(R.id.collect_toolbar)
    Toolbar collectToolbar;
    private List<Program> collects;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        setSupportActionBar(collectToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData(){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("name");
        Log.e("CollectActivity", name);
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        //查询用户收藏信息
        query.findObjects(this, new FindListener<User>(){
            @Override
            public void onSuccess(final List<User> list){
                User user = list.get(0);
                collects = user.getCollects();
                ProgramListAdapter pa = new ProgramListAdapter((ArrayList<Program>) collects, CollectActivity.this);
                listView.setAdapter(pa);
                //设置收藏条目监听，跳到播放界面
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Intent intent = new Intent(CollectActivity.this, PlayActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", collects.get(position).getUrl());
                        bundle.putString("tvName", collects.get(position).getTVName());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(int i, String s){
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return true;
    }
}
