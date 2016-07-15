package com.example.dailytv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailytv.R;
import com.example.dailytv.beans.HisProgram;
import com.example.dailytv.utils.Constant;

import java.util.List;

/**
 * Created by 荒原中的歌声 on 2016/7/13.
 */
public class HistoryRecAdapter extends RecyclerView.Adapter{

    private List<HisProgram> programs;
    private Context context;
    private LayoutInflater inflater;

    public HistoryRecAdapter(List<HisProgram> programs, Context context){
        this.programs = programs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = inflater.inflate(R.layout.history_recycleview_item, parent, false);
        HistoryViewHoder hvh = new HistoryViewHoder(item);
        return hvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        HistoryViewHoder holder1 = (HistoryViewHoder) holder;
        holder1.img.setImageResource(Constant.PICS[position % 19]);
        holder1.txt.setText(programs.get(position).getTVName());
    }

    @Override
    public int getItemCount(){
        return programs.size();
    }

    class HistoryViewHoder extends RecyclerView.ViewHolder{

      private   TextView txt;
      private   ImageView img;

        public HistoryViewHoder(View itemView){
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.history_img);
            txt = (TextView) itemView.findViewById(R.id.history__text);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(itemOnClickListener != null){
                        itemOnClickListener.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface ItemOnClickListener{
        void onClick(int position);
    }

    private ItemOnClickListener itemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener = itemOnClickListener;
    }
}
