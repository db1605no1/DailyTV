package com.example.dailytv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailytv.R;
import com.example.dailytv.beans.Program;
import com.example.dailytv.utils.Constant;

import java.util.ArrayList;

/**
 * Created by 荒原中的歌声 on 2016/7/6.
 */
public class ProgramListAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Program> list;


    public ProgramListAdapter(ArrayList<Program> list, Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ItemHolder ih;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.recycleview_item, parent, false);
            ih = new ItemHolder(convertView);
            convertView.setTag(ih);
        }else{
            ih = (ItemHolder) convertView.getTag();

        }

        ih.itemText.setText(list.get(position).getTVName());


        ih.itemImg.setImageResource(Constant.PICS[position%19]);



        return convertView;
    }

    class ItemHolder{
        ImageView itemImg;
        TextView itemText;

        public ItemHolder(View view){
            itemImg = (ImageView) view.findViewById(R.id.item_img);
            itemText = (TextView) view.findViewById(R.id.item_text);
        }
    }
}
