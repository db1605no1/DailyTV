package com.example.dailytv.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dailytv.R;
import com.example.dailytv.beans.Program;
import com.example.dailytv.fragments.fragment_play.FragmentYangshi;
import com.example.dailytv.utils.GetJsonString;
import com.example.dailytv.utils.ParseJson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends MyFragment{
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
    @Bind(R.id.play_frameLayout)
    FrameLayout playFrameLayout;
    public static List<ArrayList<Program>> playList;
    private FragmentManager manager;

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
        manager = getActivity().getSupportFragmentManager();
        initData();


    }

    private void initData(){
        String json = GetJsonString.getJsonString(getContext(), "yangshi.txt");
        playList = ParseJson.parseZhiboJson(json);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.play_frameLayout, FragmentYangshi.getInstance(playList.get(0)));
        transaction.commit();
        playRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){

                FragmentTransaction transaction = manager.beginTransaction();
                int i =playRg.indexOfChild(playRg.getChildAt(checkedId));
                Log.e("PlayFragment", "i:" + i);
               transaction.replace(R.id.play_frameLayout, FragmentYangshi.getInstance(playList.get(playRg.indexOfChild(getActivity().findViewById(checkedId)))));
               transaction.commit();

            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
