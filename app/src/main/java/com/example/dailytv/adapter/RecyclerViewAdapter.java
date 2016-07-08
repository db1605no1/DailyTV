package com.example.dailytv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailytv.R;
import com.example.dailytv.beans.TVBean;

import java.util.List;

/**
 * Created by 荒原中的歌声 on 2016/7/8.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    //    private final static int ZHIBO = 0;
    //    private final static int XINWEN = 1;
    //    private final static int YOUJIANG = 3;
    //   private List<String>names;
    private List<Integer> imgs;
    private Context context;
    private LayoutInflater inflater;
    private List<TVBean>list;


   /* @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTitle()) {
            return 0;
        } else {
            return 1;
        }
    }*/

    public RecyclerViewAdapter(List<TVBean>list, Context context) {
        //        this.names = names;
        //        this.imgs = imgs;
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item2,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        /*View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.recyclerview_item1,parent,false);
                holder = new ViewHolderOne(view);
                break;
            case 1:
                view = inflater.inflate(R.layout.recyclerview_item2,parent,false);
                holder = new ViewHolderOne(view);
                break;
        }*/


        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(list.get(position).getTitle());
        ((MyViewHolder)holder).img.setImageResource(R.mipmap.icon);
       /* switch (getItemViewType(position)) {
            case 0:
                ((ViewHolderOne)holder).title.setText(list.get(position).getTitle());
                break;
            case 1:
                ((ViewHolderTwo)holder).name.setText(list.get(position).getPrograms().get(position).getName());
                ((ViewHolderTwo)holder).img.setImageResource(R.mipmap.icon);
                break;
        }*/
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

    /*class ViewHolderOne extends RecyclerView.ViewHolder{
        private TextView title;
        public ViewHolderOne(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
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
    class ViewHolderTwo extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView img;
        public ViewHolderTwo(View itemView) {
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
*/
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

