package com.lxy.dynamicview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lxy.dynamicview.R;
import com.lxy.dynamicview.adapter.ListAdapter;
import com.lxy.dynamicview.bean.ContainerBean;
import com.lxy.dynamicview.bean.ItemBean;
import com.lxy.dynamicview.utils.OnDataResult;
import com.lxy.dynamicview.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity implements OnDataResult {

    RecyclerView rvContent;

    ListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvContent = findViewById(R.id.rv_content);

        rvContent.setAdapter(mAdapter = new ListAdapter(this));

        rvContent.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    // 模拟网络请求
    public void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String textImg = Utils.obtainDataForAssets(ListActivity.this, "left_text_right_image.json");
                String threeImg = Utils.obtainDataForAssets(ListActivity.this, "three_image.json");
                String text = Utils.obtainDataForAssets(ListActivity.this, "text.json");
                String bigImg = Utils.obtainDataForAssets(ListActivity.this, "big_image.json");


                List<HashMap<String, Object>> list = new ArrayList<>();

                list.add(Utils.toJSONMap(textImg));
                list.add(Utils.toJSONMap(threeImg));
                list.add(Utils.toJSONMap(text));
                list.add(Utils.toJSONMap(bigImg));

                HashMap<String, Object> map = new HashMap<String, Object>();

                map.put("data", list);

                final String result = new Gson().toJson(map);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onDataResult(result);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onDataResult(String result) {

        ContainerBean containerBeans = Utils.toJSONList(result, ContainerBean.class);

        mAdapter.setData(containerBeans.getData());
    }
}