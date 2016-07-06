package com.example.dailytv.fragments.fragment_play;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dailytv.PlayActivity;
import com.example.dailytv.R;
import com.example.dailytv.adapter.RecycleAdapter;
import com.example.dailytv.beans.Program;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentYangshi extends Fragment{
    @Bind(R.id.play_recycleView)
    ListView playRecycleView;
    private List<Program> list = new ArrayList<>();

    private int[] icons = {R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9
            ,R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9
    };
    private String[] names = {"CCTV1", "CCTV2", "CCTV3", "CCTV4", "CCTV5", "CCTV6", "CCTV7",
            "CCTV8","CCTV9","CCTV10","CCTV11","CCTV12","CCTV13","CCTV14","CCTV15","CHC电影"};
    private String[] urls =
            {"http://ivi.bupt.edu.cn/hls/cctv1.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv2.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv3.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv4.m3u8  ",
            "http://ivi.bupt.edu.cn/hls/cctv5.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv7.m3u8",
              "http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv9.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv10.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv11.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv12.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv13.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv14.m3u8",
                    "http://ivi.bupt.edu.cn/hls/cctv15.m3u8",
                    "http://ivi.bupt.edu.cn/hls/chchd.m3u8",

            };

    public FragmentYangshi(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_yangshi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        for(int i = 0; i < names.length; i++){
            Program p = new Program(urls[i], names[i], icons[i]);
            list.add(p);
        }
        RecycleAdapter ra = new RecycleAdapter(list, getActivity());
        playRecycleView.setAdapter(ra);
        //设置点击监听
        playRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getActivity(), PlayActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                getActivity().startActivity(intent);

            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
