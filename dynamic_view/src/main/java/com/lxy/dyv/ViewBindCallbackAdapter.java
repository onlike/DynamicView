package com.lxy.dyv;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author: lxy
 * created on: 2021/4/6 7:07 PM
 * <p>
 */
public class ViewBindCallbackAdapter implements IViewBindCallback{

    public ViewBindCallbackAdapter() {
    }

    public boolean textBind(TextView textView, String textContent){
        return false;
    }

    public boolean imageBind(ImageView imageView, String propertyValue, @BindImageType int bindType) {
        return false;
    }

    @Override
    public boolean viewBind(View targetView, String method, String value) {

        try {

            if (TextUtils.equals(method, DyvConstant.VIEW_METHOD_SET_TEXT)) {

                return textBind((TextView) targetView, value);
            } else if (TextUtils.equals(method, DyvConstant.VIEW_METHOD_SET_IMAGE)) {

                return imageBind((ImageView) targetView, value, imgBindType(value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private int imgBindType(String propertyValue) {

        int imgBindType = DyvHelper.isURLValue(propertyValue) ?
                DyvConstant.IMAGE_BIND_TYPE_URL : DyvHelper.isColorValue(propertyValue) ?
                DyvConstant.IMAGE_BIND_TYPE_COLOR : DyvConstant.IMAGE_BIND_TYPE_DRAWABLE;

        return imgBindType;
    }

}
