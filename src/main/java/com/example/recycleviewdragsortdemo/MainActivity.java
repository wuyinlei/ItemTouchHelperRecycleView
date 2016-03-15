package com.example.recycleviewdragsortdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mList;
    private RecyclerView mRecycleView;
    private RecycleViewAdapter mAdapter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        initRecycleView();

        initListener();
    }

    private void initListener() {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecycleView);
    }

    private void initRecycleView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new RecycleViewAdapter(this,mList);
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setItemClick(new RecycleViewAdapter.OnItemClick() {
            @Override
            public void onClick(View v, int position, String item) {

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("item" + i);
        }
    }
}
