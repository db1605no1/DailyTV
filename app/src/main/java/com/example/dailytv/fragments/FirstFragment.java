package com.example.dailytv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dailytv.R;
import com.example.dailytv.StoreDetalsActivity;
import com.example.dailytv.adapter.FirstRecyclerViewAdapter;
import com.example.dailytv.adapter.ShouYeLunBoAdapter;
import com.example.dailytv.beans.Program;
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
public class FirstFragment extends MyFragment{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.vp_shouye_lunbo)
    ViewPager viewPager;
    @Bind(R.id.tv_lunbo)
    TextView tv_lunbo;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.iv_red_point)
    ImageView ivRedPoint;
    @Bind(R.id.header)
    RecyclerViewHeader header;
    private List<String> strList = new ArrayList<>();
    private List<Integer> imgs = new ArrayList<>();
    private String[] titles = {"一二三四五", "上山打老虎", "老虎打不到", "打到小松鼠", "六六六六六"};
    private int[] mImageIds = {R.mipmap.lin13, R.mipmap.lin19, R.mipmap.lin20, R.mipmap.lin21, R.mipmap.lin22};
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 0:
                    int currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    if(currentItem > mImageViewList.size() - 1){
                        currentItem = 0;
                    }
                    viewPager.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                    break;
            }
        }
    };
    private int mPointDis;
    private List<ImageView> mImageViewList = new ArrayList<>();
    public static List<Store> firstList;
    private FirstRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){
        initData();
        String json = GetJsonString.getJsonString(getContext(), "first.txt");
        firstList = ParseJson.parseStoreJson(json);
        initRecyclerView();
        viewPager.setAdapter(new ShouYeLunBoAdapter(mImageViewList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                // 当页面滑动过程中的回调
                //System.out.println("当前位置:" + position + ";移动偏移百分比:"
                //       + positionOffset);
                // 更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset) + position * mPointDis;// 计算小红点当前的左边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                params.leftMargin = leftMargin;// 修改左边距
                // 重新设置布局参数
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position){
                tv_lunbo.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout(){
                // 移除监听,避免重复回调
                ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);// 发送延时3秒的消息
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    private void initRecyclerView(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        header.attachTo(recyclerView, true);
        adapter = new FirstRecyclerViewAdapter(firstList, getActivity());
        adapter.setOnItemClickListener(new FirstRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                intentActivity(position);
            }
        });
        recyclerView.setAdapter(adapter);


    }

    private void intentActivity(int position){
        List<Program> programs = FirstFragment.this.firstList.get(position).getList();
        Intent intent = new Intent(getActivity(), StoreDetalsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("programs", (ArrayList<? extends Parcelable>) programs);
        bundle.putString("text", firstList.get(position).getText());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    private void initData(){
        mImageViewList = new ArrayList<ImageView>();
        for(int i = 0; i < mImageIds.length; i++){
            ImageView view = new ImageView(getActivity());
            view.setBackgroundResource(mImageIds[i]);// 通过设置背景,可以让宽高填充布局
            // view.setImageResource(resId)
            mImageViewList.add(view);
            // 初始化小圆点
            ImageView point = new ImageView(getActivity());
            point.setImageResource(R.drawable.shape_point_gray);// 设置图片(shape形状)
            // 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i > 0){
                // 从第二个点开始设置左边距
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);// 设置布局参数
            if(llContainer != null){
                llContainer.addView(point);// 给容器添加圆点
            }
        }
    }
}
