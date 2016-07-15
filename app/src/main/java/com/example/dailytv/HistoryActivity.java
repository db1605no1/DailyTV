package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.dailytv.adapter.HistoryRecAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity{

    @Bind(R.id.history_toolbar)
    Toolbar historyToolbar;
    @Bind(R.id.history_rec_view)
    RecyclerView historyRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        setSupportActionBar(historyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();
    }

    private void initData(){
        HistoryRecAdapter  hra=new HistoryRecAdapter(MainActivity.history,this);
        historyRecView.setAdapter(hra);
        RecyclerView.LayoutManager manager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        historyRecView.setLayoutManager(manager);
        hra.setItemOnClickListener(new HistoryRecAdapter.ItemOnClickListener(){
            @Override
            public void onClick(int position){
                Intent intent = new Intent(HistoryActivity.this, PlayActivity.class);
                Bundle  bundle1=new Bundle();
                bundle1.putString("tvName",MainActivity.history.get(position).getTVName());
                bundle1.putString("url", MainActivity.history.get(position).getUrl());
                intent.putExtras(bundle1);
                startActivity(intent);
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
        return super.onOptionsItemSelected(item);
    }
}
