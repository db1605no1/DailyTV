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
public class FragmentProvince extends Fragment{

    @Bind(R.id.play_recycleView)
    ListView playRecycleView;

    private List<Program> list = new ArrayList<>();

    private int[] icons = {R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9
            ,R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
            R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9
    };
    private String[] names = {"北京卫视HD", "湖南卫视HD", "浙江卫视HD", "东方卫视HD",
            "山东卫视HD", "江苏卫视HD", "深圳卫视HD", "黑龙江卫视HD",
            "广东卫视","河南卫视","辽宁卫视","深圳卫视",
            "天津卫视", "山西卫视","河北卫视","甘肃卫视"};
    private String[] urls =
            {"http://ivi.bupt.edu.cn/hls/btv1hd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/hunanhd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/zjhd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/dfhd.m3u8 ",
                    "http://ivi.bupt.edu.cn/hls/sdhd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/jshd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/szhd.m3u8",
                    "http://ivi.bupt.edu.cn/hls/hljhd.m3u8",


                    "http://106.120.175.80:55336/tslive/c27_ct_gdtv1_gdws_smooth_t10/c27_ct_gdtv1_gdws_smooth_t10.m3u8",
                    "http://106.120.175.80:55336/tslive/c12_ct_hntv1_henws_smooth_t10/c12_ct_hntv1_henws_smooth_t10.m3u8",   "http://ivi.bupt.edu.cn/hls/cctv11.m3u8",
                    "http://106.120.175.80:55336/tslive/c29_ct_lntv1_lnws_smooth_t10/c29_ct_lntv1_lnws_smooth_t10.m3u8",
                    "http://106.120.175.80:55336/tslive/c8_ct_sztv1_szws_smooth_t10/c8_ct_sztv1_szws_smooth_t10.m3u8",
                    "http://106.120.175.80:55336/tslive/c23_ct_tjtv1_tjws_smooth_t10/c23_ct_tjtv1_tjws_smooth_t10.m3u8",
                    "http://106.120.175.80:55336/tslive/c24_ct_sxtv1_shxws_smooth_t10/c24_ct_sxtv1_shxws_smooth_t10.m3u8",
                    "http://106.120.175.80:55336/tslive/c8_ct_hebei1_hebws_smooth_t10/c8_ct_hebei1_hebws_smooth_t10.m3u8",
                     "http://106.120.175.80:55336/tslive/c28_ct_gstv1_gsws_smooth_t10/c28_ct_gstv1_gsws_smooth_t10.m3u8"
            };

//    北京卫视HD,http://ivi.bupt.edu.cn/hls/btv1hd.m3u8
//    湖南卫视HD,http://ivi.bupt.edu.cn/hls/hunanhd.m3u8
//    浙江卫视HD,http://ivi.bupt.edu.cn/hls/zjhd.m3u8
//    东方卫视HD,http://ivi.bupt.edu.cn/hls/dfhd.m3u8
//    山东卫视HD,http://ivi.bupt.edu.cn/hls/sdhd.m3u8
//    江苏卫视HD,http://ivi.bupt.edu.cn/hls/jshd.m3u8
//    深圳卫视HD,http://ivi.bupt.edu.cn/hls/szhd.m3u8
//    黑龙江卫视HD,http://ivi.bupt.edu.cn/hls/hljhd.m3u8
    public FragmentProvince(){
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
