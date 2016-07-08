package com.example.dailytv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailytv.R;
import com.example.dailytv.beans.Store;

import java.util.List;

/**
 * Created by Administrator on 16-7-6.
 */
public class StoreRecycleAdapter extends RecyclerView.Adapter{
    private List<Store> list;
    private Context context;
    private LayoutInflater inflater;
    private Intent intent;

    public StoreRecycleAdapter(List<Store> list, Context context){
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=inflater.inflate(R.layout.recycleview_layout,parent,false);
        MyStoreViewHold msvh=new MyStoreViewHold(view);
        return msvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        ((MyStoreViewHold) holder).storeImg.setImageResource(R.drawable.aadefault);
        ((MyStoreViewHold) holder).storeText.setText(list.get(position).getText());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    private class MyStoreViewHold extends RecyclerView.ViewHolder{
        private ImageView storeImg;
        private TextView storeText;
        public MyStoreViewHold(View itemView){
            super(itemView);
            storeImg= (ImageView) itemView.findViewById(R.id.store_img);
            storeText= (TextView) itemView.findViewById(R.id.store_text);
            storeImg.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast.makeText(context, getLayoutPosition()+"", Toast.LENGTH_SHORT).show();
                    if(onRecycleListen!=null){
                        onRecycleListen.setOnRecycleListen(getLayoutPosition());
                    }
                }
            });

        }
    }

    public interface OnRecycleListen{
        public  void setOnRecycleListen(int position);
    }

    private OnRecycleListen onRecycleListen;

    public void setOnRecycleListen(OnRecycleListen onRecycleListen){
        this.onRecycleListen=onRecycleListen;
    }



}
