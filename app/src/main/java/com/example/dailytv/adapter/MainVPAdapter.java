package com.example.dailytv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by 荒原中的歌声 on 2016/7/5.
 */
public class MainVPAdapter extends FragmentPagerAdapter{
    public MainVPAdapter(FragmentManager fm,List<Fragment>  list){
        super(fm);
        this.list=list;

    }

    String [] titles={"首页","电视直播","片库","发现"};
    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }

    private List<Fragment>  list;
    @Override
    public Fragment getItem(int position){
        return list.get(position);
    }

    @Override
    public int getCount(){
        return list.size();
    }
}
