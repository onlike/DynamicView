package com.lxy.dynamicview;

import android.app.Application;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lxy.dyv.DynamicMaster;
import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.ImageBindListener;

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

        DynamicMaster.registerImageBindListener(new ImageBindListener() {
            @Override
            public boolean imageBind(ImageView imageView, String value, int bindType) {

                if (bindType == DyvConstant.IMAGE_BIND_TYPE_URL) {

                    Glide.with(imageView.getContext()).load(value).into(imageView);
                    return true;
                }

                return false;
            }
        });
    }
}
