package com.lxy.dynamicview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxy.dynamicview.R;
import com.lxy.dynamicview.utils.OnDataResult;
import com.lxy.dynamicview.utils.Utils;
import com.lxy.dyv.DynamicMaster;
import com.lxy.dyv.DynamicView;
import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.IEventTouchCallback;
import com.lxy.dyv.ViewBindCallbackAdapter;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FrameLayout fraContent;

    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fraContent = findViewById(R.id.fra_content);
        tvMsg = findViewById(R.id.tv_msg);

        findViewById(R.id.fab_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        getData(new OnDataResult() {
            @Override
            public void onDataResult(String result) {

                HashMap<String, Object> data = Utils.toJSONMap(result);

                DynamicView dynamicView = DynamicMaster.get(MainActivity.this)
                        .injectViewData((Map<String, Object>) data.get("template"))
                        .injectPropertiesData((Map<String, Object>) data.get("properties"))
                        .build();

                View rootView = dynamicView.bindView();

                fraContent.addView(rootView);

                dynamicView.bindData(new ViewBindCallbackAdapter(){
                    @Override
                    public boolean imageBind(ImageView imageView, String propertyValue, int bindType) {

                        if (bindType == DyvConstant.IMAGE_BIND_TYPE_URL) {

                            Glide.with(imageView.getContext()).load(propertyValue).into(imageView);
                            return true;
                        }

                        return super.imageBind(imageView, propertyValue, bindType);
                    }
                });

                dynamicView.bindEventTouch(new IEventTouchCallback() {
                    @Override
                    public void onEventTouch(View view, Event event, Data data) {

//                        Utils.showToast(MainActivity.this, data, event);
                        tvMsg.setText(Utils.getMsg(event, data));
                    }
                });
            }
        });
    }

    public void getData(final OnDataResult onDataResult) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final String msg = Utils.obtainDataForAssets(MainActivity.this, "three_image.json");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onDataResult.onDataResult(msg);
                    }
                });

            }
        }).start();
    }
}