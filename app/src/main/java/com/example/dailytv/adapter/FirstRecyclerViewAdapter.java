package com.example.dailytv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailytv.R;
import com.example.dailytv.beans.Store;
import com.example.dailytv.utils.Constant;

import java.util.List;

/**
 * Created by 荒原中的歌声 on 2016/7/8.
 */
public class FirstRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
     private    List<Store> list;




    public FirstRecyclerViewAdapter(  List<Store> list, Context context) {

        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item2,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(list.get(position).getText());
        ((MyViewHolder)holder).img.setImageResource(Constant.PICS1[position%19]);

    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_rv_item);
            img = (ImageView) itemView.findViewById(R.id.iv_rv_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });


        }

    }

    //1.定义一个接口
    //2.声明一个接口变量
    //3.初始化接口变量
    //4.在点击事件中调用接口里的方法
    //5.在Activity中实现接口
    public  interface OnItemClickListener{
        void onItemClick(int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

