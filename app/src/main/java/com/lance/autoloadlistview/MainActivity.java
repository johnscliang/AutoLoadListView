package com.lance.autoloadlistview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AutoLoadListView.IonLoadMore {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AutoLoadListView mLvData;
    private ArrayAdapter mAdapter;
    private List<String> data;//the list of data
    private Context mContext;
    private Handler mHandler = new Handler();
    private int mPage;//current page
    private final int PAGE_MAX = 10;//last page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //find view
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mLvData = (AutoLoadListView) findViewById(R.id.lv_data);
        //init
        mSwipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_red_light);
        //set listener
        mSwipeRefreshLayout.setOnRefreshListener(this);//call this method before set adapter
        mLvData.setOnLoadmoreListener(this,mContext);
        //ini data
        data = new ArrayList<>();
        addMoreData();
        mAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,android.R.id.text1,data);
        mLvData.setAdapter(mAdapter);
    }
    //add more data
    private void addMoreData() {
        int size = data.size();
        for (int i = 0; i < 20; i++) {
            data.add(String.valueOf(size+i));
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        data.clear();
        mAdapter.notifyDataSetChanged();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addMoreData();
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onLoadMore() {
        mLvData.setLoading(true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addMoreData();
                mAdapter.notifyDataSetChanged();
                mLvData.setLoading(false);
            }
        },3000);
    }
}
