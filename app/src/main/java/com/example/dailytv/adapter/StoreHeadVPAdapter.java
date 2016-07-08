package com.example.dailytv.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 16-7-7.
 */
public class StoreHeadVPAdapter extends PagerAdapter{
    private List<ImageView> list;
    private Context context;
   // private LayoutInflater inflater;

    public StoreHeadVPAdapter(List<ImageView> list, Context context){
        this.list = list;
        this.context = context;
       // inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return list.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView view = list.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView(list.get(position));
    }
}
