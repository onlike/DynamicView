package com.lxy.dynamicview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.dynamicview.R;
import com.lxy.dynamicview.bean.ItemBean;
import com.lxy.dynamicview.utils.Utils;
import com.lxy.dyv.DynamicMaster;
import com.lxy.dyv.DynamicView;
import com.lxy.dyv.IEventTouchCallback;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lxy
 * created on: 2021/3/14 2:10 PM
 * <p>
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context mContext;

    List<ItemBean> mData;

    public ListAdapter(Context context) {
        this.mContext = context;

        this.mData = new ArrayList<>();
    }

    public void setData(List<ItemBean> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_item_container, viewGroup, false);
        return new LVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((LVH) viewHolder).bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class LVH extends RecyclerView.ViewHolder{



        public LVH(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(ItemBean itemBean){


            DynamicView dynamicView = DynamicMaster.get(mContext)
                    .injectViewData(itemBean.getTemplate())
                    .injectPropertiesData(itemBean.getProperties())
                    .build();

            View rootView = dynamicView.bindView();

            ((ViewGroup)itemView).removeAllViews();

            ((ViewGroup) itemView).addView(rootView);

            dynamicView.bindData();

            dynamicView.bindEventTouch(new IEventTouchCallback() {
                @Override
                public void onEventTouch(View view, Event event, Data data) {

                        Utils.showToast(mContext, data, event);
                }
            });

        }
    }
}
