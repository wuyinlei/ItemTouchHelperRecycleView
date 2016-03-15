package com.example.recycleviewdragsortdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 若兰 on 2016/3/15.
 * 一个懂得了编程乐趣的小白，希望自己
 * 能够在这个道路上走的很远，也希望自己学习到的
 * 知识可以帮助更多的人,分享就是学习的一种乐趣
 * QQ:1069584784
 * csdn:http://blog.csdn.net/wuyinlei
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewholder> implements TouchHelperAdapter{

    private List<String> mLists = new ArrayList<>();
    private LayoutInflater mInflater;

    OnItemClick mItemClick;

    public void setItemClick(OnItemClick itemClick) {
        mItemClick = itemClick;
    }

    public RecycleViewAdapter(Context context, List<String> lists) {
        mLists = lists;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item,parent,false);

        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.mTextView.setText(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String prev = mLists.remove(fromPosition);
        mLists.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mLists.remove(position);
        notifyItemChanged(position);
    }

    class MyViewholder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        private TextView mTextView;

        public MyViewholder(View itemView) {
            super(itemView);
            if (itemView != null) {
                mTextView = (TextView) itemView.findViewById(R.id.item_list);
                mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClick != null){
                            mItemClick.onClick(v,getLayoutPosition(),mLists.get(getLayoutPosition()));
                        }
                    }
                });
            }
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.GRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public interface OnItemClick{
        void onClick(View v, int position, String item);
    }
}
