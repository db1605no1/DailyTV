package com.example.dailytv.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dailytv.R;
import com.example.dailytv.adapter.RecyclerViewAdapter;
import com.example.dailytv.adapter.ShouYeLunBoAdapter;
import com.example.dailytv.beans.TVBean;
import com.example.dailytv.utils.ParseJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends MyFragment{

    private List<String> strList = new ArrayList<>();
    private List<Integer> imgs = new ArrayList<>();
    private String[] titles = {"11111", "22222", "33333", "44444", "55555"};
    private int[] mImageIds = {R.mipmap.lin13, R.mipmap.lin19, R.mipmap.lin20, R.mipmap.lin21, R.mipmap.lin22};
    private Handler mHandler;
    private ImageView ivRedPoint;// 小红点
    private int mPointDis;
    private LinearLayout llContainer;
    private List<ImageView> mImageViewList = new ArrayList<>();
    private TextView tv_lunbo;
    private RecyclerView recyclerView;
    private RecyclerViewHeader header;
    private List<TVBean> tvBeanListV = new ArrayList<>();
    private RecyclerViewAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_shouye_lunbo);
        header = (RecyclerViewHeader) view.findViewById(R.id.header);
        tv_lunbo = (TextView) view.findViewById(R.id.tv_lunbo);
        ivRedPoint = (ImageView) view.findViewById(R.id.iv_red_point);
        llContainer = (LinearLayout) view.findViewById(R.id.ll_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initData();
        viewPager.setAdapter(new ShouYeLunBoAdapter(mImageViewList));
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {


                    switch (msg.what) {
                        case 0:
                            int currentItem = viewPager.getCurrentItem();
                            currentItem++;
                            if (currentItem > mImageViewList.size() - 1) {
                                currentItem = 0;
                            }
                            viewPager.setCurrentItem(currentItem);
                            mHandler.sendEmptyMessageDelayed(0, 3000);
                            break;
                        case 1:
                            String json = (String) msg.obj;
                            tvBeanListV = ParseJson.parseJson2TVBean(json);
                            Log.d("ShouyeFragment", "tvBeanListV.size():________________" + tvBeanListV.size());
                            //adapter.notifyDataSetChanged();
                            initRecyclerView();
                            break;
                    }
                }
            };
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 当页面滑动过程中的回调
                //System.out.println("当前位置:" + position + ";移动偏移百分比:"
                //       + positionOffset);
                // 更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;// 计算小红点当前的左边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
                        .getLayoutParams();
                params.leftMargin = leftMargin;// 修改左边距

                // 重新设置布局参数
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                tv_lunbo.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除监听,避免重复回调
                ivRedPoint.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
                // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // layout方法执行结束的回调
                mPointDis = llContainer.getChildAt(1).getLeft()
                        - llContainer.getChildAt(0).getLeft();
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 3000);// 发送延时3秒的消息

    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //        for (int i = 0; i < tvBeanListV.size(); i++) {
        //            // strList.add("中央电视台"+i);
        //            imgs.add(R.mipmap.icon);
        //        }
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        header.attachTo(recyclerView, true);
      /*  if () {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            // manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(manager);
        } else {
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(manager);
        }*/


        Log.d("ShouyeFragment", "tvBeanListV.size():+++++++++++++++++++++++" + tvBeanListV.size());
        adapter = new RecyclerViewAdapter(tvBeanListV, getActivity());
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "position:" +tvBeanListV.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(getActivity());
            view.setBackgroundResource(mImageIds[i]);// 通过设置背景,可以让宽高填充布局
            // view.setImageResource(resId)
            mImageViewList.add(view);
            // 初始化小圆点
            ImageView point = new ImageView(getActivity());
            point.setImageResource(R.drawable.shape_point_gray);// 设置图片(shape形状)
            // 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                // 从第二个点开始设置左边距
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);// 设置布局参数
            if (llContainer != null) {
                llContainer.addView(point);// 给容器添加圆点
            }
        }

        //从网络获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assets = getResources().getAssets();
                StringBuffer sb = new StringBuffer();
                BufferedReader br = null;
                try {
                    InputStream is = assets.open("yangshi.txt");
                    br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    String json = sb.toString();
                    Message message = mHandler.obtainMessage();
                    message.obj = json;
                    message.what = 1;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }



}
