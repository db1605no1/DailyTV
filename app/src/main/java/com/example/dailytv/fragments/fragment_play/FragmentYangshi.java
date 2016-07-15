package com.example.dailytv.fragments.fragment_play;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dailytv.PlayActivity;
import com.example.dailytv.R;
import com.example.dailytv.adapter.ProgramListAdapter;
import com.example.dailytv.beans.Program;
import com.example.dailytv.fragments.MyFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentYangshi extends MyFragment{
    @Bind(R.id.play_recycleView)
    ListView playRecycleView;

private   ArrayList<Program> programs;
    public  static  FragmentYangshi  getInstance(ArrayList<Program> list ){


        FragmentYangshi   fy=new FragmentYangshi();
        Bundle  bundle=new Bundle();
        bundle.putParcelableArrayList("list",list);
        fy.setArguments(bundle);
        return   fy;
    }




    public FragmentYangshi(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_yangshi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        final Bundle bundle = this.getArguments();
        programs=bundle.getParcelableArrayList("list");
        ProgramListAdapter ra = new ProgramListAdapter(programs, getActivity());
        playRecycleView.setAdapter(ra);
        ra.notifyDataSetChanged();
        //设置点击监听
        playRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getActivity(), PlayActivity.class);
                Bundle  bundle1=new Bundle();
                bundle1.putString("tvName",programs.get(position).getTVName());
                bundle1.putString("url", programs.get(position).getUrl());
                  intent.putExtras(bundle1);
                getActivity().startActivity(intent);

            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
