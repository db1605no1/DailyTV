package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dailytv.beans.Program;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetalsActivity extends AppCompatActivity{
    @Bind(R.id.store_listView)
    ListView storeListView;
    private List<Program> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detals);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getParcelableArrayList("programs");
        initView();
    }

    private void initView(){
        List<String> list0 = new ArrayList<>();
        for(Program program : list){
            Log.e("StoreDetalsActivity", "program:" + program);
            list0.add(program.getTVName());
        }
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list0);
        storeListView.setAdapter(aa);
        //设置点击事件，跳到播放页面
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(StoreDetalsActivity.this, PlayActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }
}
