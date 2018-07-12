package com.example.administrator.xrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tan.xrecyclerview.ProgressStyle;
import com.tan.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    XRecyclerView recyclerView;
    List<Integer> data=new ArrayList<>();
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(XRecyclerView)findViewById(R.id.xrecy);
        LinearLayoutManager xLinearLayoutManager = new LinearLayoutManager(this);
        //xLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(xLinearLayoutManager);
        View header= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_head,(ViewGroup)findViewById(android.R.id.content),false);
        View header1= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_head,(ViewGroup)findViewById(android.R.id.content),false);
        View header2= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_head,(ViewGroup)findViewById(android.R.id.content),false);
        recyclerView.addHeaderView(header);     //添加头部
        recyclerView.addHeaderView(header1);     //添加头部
        recyclerView.addHeaderView(header2);     //添加头部
        recyclerView.setRefreshProgressStyle(ProgressStyle.Pacman); //设定下拉刷新样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);//设定上拉加载样式
        // recyclerView.setArrowImageView(R.drawable.ic_loading_rotate);     //设定下拉刷新显示图片（不必须）
        recyclerView.setNoMoreHint("--已经到底了--");
        recyclerView.setLoadingHint("正在加载");
        recyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        initData();   //初始化数据
        adapter=new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        /**
         *设定下拉刷新和上拉加载监听
         */
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //上拉加载监听
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();  //上拉加载添加数据
                        recyclerView.loadMoreComplete();    //加载数据完成（取消加载动画）
                        recyclerView.setNoMore(true);
                    }
                },2000);
            }
            //下拉刷新监听
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        initData();     //初始化数据
                        adapter.notifyDataSetChanged();
                        recyclerView.refreshComplete();     //刷新数据完成（取消刷新动画）
                    }
                },2000);

            }
        });

    }

    /**
     *上拉加载添加数据
     */
    private void addData() {
        if (data.size()<20){
            for (int i=0;i<5;i++){
                Integer r= Integer.valueOf((int) (Math.random()*100));
                data.add(r);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     *初始化数据
     */
    private void initData() {
        data.clear();
        for (int i=0;i<20;i++){
            Integer r= Integer.valueOf(i);
            data.add(r);
        }

    }
}
