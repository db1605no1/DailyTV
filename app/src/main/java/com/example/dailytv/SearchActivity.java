package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.dailytv.adapter.ProgramListAdapter;
import com.example.dailytv.beans.Program;
import com.example.dailytv.beans.Store;
import com.example.dailytv.fragments.FirstFragment;
import com.example.dailytv.fragments.PlayFragment;
import com.example.dailytv.fragments.StoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity{

    @Bind(R.id.search_edit)
    EditText searchEdit;
    @Bind(R.id.search_start)
    ImageButton searchStart;
    @Bind(R.id.search_listView)
    ListView searchListView;
    private List<Program> list = new ArrayList();
    private List<Program> list2 = new ArrayList();
    private ProgramListAdapter pla;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        for(Store store : StoreFragment.storeList0){
            list.addAll(store.getList());
        }
        for(ArrayList<Program> programs : PlayFragment.playList){
            list.addAll(programs);
        }
        for(Store store : FirstFragment.firstList){
            list.addAll(store.getList());
        }
        pla = new ProgramListAdapter((ArrayList<Program>) list2, this);
        searchListView.setAdapter(pla);
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(SearchActivity.this, PlayActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("tvName", list2.get(position).getTVName());
                bundle1.putString("url", list2.get(position).getUrl());
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }

    public void search(View view){
        String str = searchEdit.getText().toString();
        list2.clear();
        for(Program program : list){
            if((program.getTVName().contains(str))){
                list2.add(program);
            }
        }
        pla.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
