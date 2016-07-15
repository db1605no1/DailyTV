package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dailytv.adapter.ProgramListAdapter;
import com.example.dailytv.beans.Program;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetalsActivity extends AppCompatActivity{
    @Bind(R.id.store_listView)
    ListView storeListView;
    @Bind(R.id.detail_toolbar)
    Toolbar detailToolbar;
    private ArrayList<Program> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detals);
        ButterKnife.bind(this);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getParcelableArrayList("programs");
        initView();
    }

    private void initView(){
        ProgramListAdapter pa = new ProgramListAdapter(list, this);
        storeListView.setAdapter(pa);
        //设置点击事件，跳到播放页面
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(StoreDetalsActivity.this, PlayActivity.class);
                Bundle  bundle1=new Bundle();
                bundle1.putString("tvName",list.get(position).getTVName());
                bundle1.putString("url", list.get(position).getUrl());
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
