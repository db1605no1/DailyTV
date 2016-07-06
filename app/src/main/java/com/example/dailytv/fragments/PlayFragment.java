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
import com.example.dailytv.fragments.fragment_play.CartoonFragment;
import com.example.dailytv.fragments.fragment_play.CultureFragment;
import com.example.dailytv.fragments.fragment_play.EconomicFragment;
import com.example.dailytv.fragments.fragment_play.ExploreFragment;
import com.example.dailytv.fragments.fragment_play.FoodFragment;
import com.example.dailytv.fragments.fragment_play.FragmentAbroad;
import com.example.dailytv.fragments.fragment_play.FragmentArtical;
import com.example.dailytv.fragments.fragment_play.FragmentNews;
import com.example.dailytv.fragments.fragment_play.FragmentProvince;
import com.example.dailytv.fragments.fragment_play.FragmentScience;
import com.example.dailytv.fragments.fragment_play.FragmentSports;
import com.example.dailytv.fragments.fragment_play.FragmentYangshi;
import com.example.dailytv.fragments.fragment_play.FunFragment;
import com.example.dailytv.fragments.fragment_play.LifeFragment;
import com.example.dailytv.fragments.fragment_play.MovieFragment;
import com.example.dailytv.fragments.fragment_play.MusicFragment;
import com.example.dailytv.fragments.fragment_play.RecoderFragment;
import com.example.dailytv.fragments.fragment_play.WomenFragment;
import com.example.dailytv.fragments.fragment_play.XijuFragment;
import com.example.dailytv.views.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment{
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

    private List<Fragment> list = new ArrayList<>();

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
        list.add(new FragmentYangshi());
        list.add(new FragmentProvince());
        list.add(new FragmentNews());
        list.add(new FragmentSports());
        list.add(new FragmentScience());
        list.add(new FragmentArtical());
        list.add(new FragmentAbroad());
        list.add(new LifeFragment());
        list.add(new FoodFragment());
        list.add(new FunFragment());
        list.add(new WomenFragment());
        list.add(new CartoonFragment());
        list.add(new MovieFragment());
        list.add(new XijuFragment());
        list.add(new MusicFragment());
        list.add(new EconomicFragment());
        list.add(new CultureFragment());
        list.add(new ExploreFragment());
        list.add(new RecoderFragment());
        PlayAdapter fa = new PlayAdapter(getActivity().getSupportFragmentManager(), list);
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
