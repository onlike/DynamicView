package com.lxy.dynamicview;

import android.app.Application;

import com.lxy.dyv.DynamicMaster;

/**
 * author: lxy
 * created on: 2021/3/14 12:56 PM
 * <p>
 */
public class LApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        DynamicMaster.init(this);
    }
}
