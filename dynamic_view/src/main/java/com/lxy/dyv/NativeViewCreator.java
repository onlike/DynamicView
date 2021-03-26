package com.lxy.dyv;

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author: lxy
 * created on: 2021/2/23 5:58 PM
 * <p>
 */
public final class NativeViewCreator {

    @IntDef({DyvConstant.NATIVE_VIEW_TYPE_TEXT,
            DyvConstant.NATIVE_VIEW_TYPE_IMAGE,
            DyvConstant.NATIVE_VIEW_TYPE_LINEAR,
            DyvConstant.NATIVE_VIEW_TYPE_FRAME
    })
    @Retention(RetentionPolicy.RUNTIME)
    @interface NativeViewType{
    }

    public static <T extends View> T createNativeView(@NativeViewType int nativeViewType, Context context) {

        View target = null;

        switch (nativeViewType) {

            case DyvConstant.NATIVE_VIEW_TYPE_TEXT:
                target = new TextView(context);
                break;

            case DyvConstant.NATIVE_VIEW_TYPE_IMAGE:
                target = new ImageView(context);
                break;

            case DyvConstant.NATIVE_VIEW_TYPE_LINEAR:
                target = new LinearLayout(context);
                break;

            case DyvConstant.NATIVE_VIEW_TYPE_FRAME:
                target = new FrameLayout(context);
                break;

            default:
                break;
        }

        return (T) target;
    }
}
