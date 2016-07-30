package com.example.dailytv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dailytv.R;
import com.example.dailytv.StoreDetalsActivity;
import com.example.dailytv.adapter.StoreHeadVPAdapter;
import com.example.dailytv.adapter.StoreRecycleAdapter;
import com.example.dailytv.beans.Store;
import com.example.dailytv.utils.GetJsonString;
import com.example.dailytv.utils.ParseJson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment{
    @Bind(R.id.store_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.store_vp)
    ViewPager store_vp;
    @Bind(R.id.store_vp_text)
    TextView store_text;
    @Bind(R.id.recycle_head)
    RecyclerViewHeader recy_head;
    @Bind(R.id.store_show)
    Button storeShow;
    private static Boolean show;
    private int haha = 0;
    private List<ImageView> list_vp = null;
    private int imgs[] = {R.mipmap.hearder1, R.mipmap.hearder2, R.mipmap.hearder3, R.mipmap.hearder4};
    private final String[] storeText = {"one", "two", "three", "four"};
    private StoreRecycleAdapter sra;
    private Intent intent;
    public static List<Store> storeList0;

    public StoreFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initViewPage();
        StoreHeadVPAdapter storeHeadVPAdapter = new StoreHeadVPAdapter(list_vp, getContext());
        store_vp.setAdapter(storeHeadVPAdapter);
        GridLayoutManager gridlayout = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridlayout);
        final String str = GetJsonString.getJsonString(getContext(), "store.txt");
        storeList0 = ParseJson.parseStoreJson(str);
        sra = new StoreRecycleAdapter(storeList0, getContext());
        recyclerView.setAdapter(sra);
        recy_head.attachTo(recyclerView, true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);
                int childCount = recyclerView.getChildCount();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        store_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            }

            @Override
            public void onPageSelected(int position){
                store_text.setText(storeText[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        sra.setOnRecycleListen(new StoreRecycleAdapter.OnRecycleListen(){
            @Override
            public void OnRecycleListen(int position){
                intentActivity(position);
            }
        });
        storeShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                haha++;
                if(haha >= 10){
                    sra.setShow();
                }
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    private void intentActivity(int position){
        Intent intent = new Intent(getActivity(), StoreDetalsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("programs", (ArrayList<? extends Parcelable>) storeList0.get(position).getList());
        bundle.putString("text", storeList0.get(position).getText());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    private void initViewPage(){
        list_vp = new ArrayList<>();
        for(int i = 0; i < imgs.length; i++){
            ImageView iv = new ImageView(getActivity());
            iv.setBackgroundResource(imgs[i]);
            list_vp.add(iv);
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
