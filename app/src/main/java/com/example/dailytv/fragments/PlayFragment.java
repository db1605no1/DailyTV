package com.example.dailytv.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dailytv.R;
import com.example.dailytv.adapter.PlayAdapter;
import com.example.dailytv.beans.Program;
import com.example.dailytv.fragments.fragment_play.FragmentYangshi;
import com.example.dailytv.utils.GetJsonString;
import com.example.dailytv.utils.ParseJson;
import com.example.dailytv.views.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends MyFragment{
    @Bind(R.id.play_viewPager)
    MyViewPager playViewPager;
    @Bind(R.id.play_bt1)
    RadioButton playBt1;
    @Bind(R.id.play_bt2)
    RadioButton playBt2;
    @Bind(R.id.play_bt3)
    RadioButton playBt3;
    @Bind(R.id.play_bt4)
    RadioButton playBt4;
    @Bind(R.id.play_bt5)
    RadioButton playBt5;
    @Bind(R.id.play_bt6)
    RadioButton playBt6;
    @Bind(R.id.play_bt7)
    RadioButton playBt7;
    @Bind(R.id.play_rg)
    RadioGroup playRg;
    private List<ArrayList<Program>> list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public PlayFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData(){
        String json = GetJsonString.getJsonString(getContext(), "yangshi.txt");
        list.addAll(ParseJson.parseZhiboJson(json));
        for(int i = 0; i < list.size(); i++){
            fragments.add(FragmentYangshi.getInstance(list.get(i)));
        }
        PlayAdapter fa = new PlayAdapter(getActivity().getSupportFragmentManager(), fragments);
        playViewPager.setAdapter(fa);
        playViewPager.setPagingEnabled(false);
        playRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                playViewPager.setCurrentItem(playRg.indexOfChild(getActivity().findViewById(checkedId)));
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
