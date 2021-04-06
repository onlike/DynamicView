package com.lxy.dyv.transform.help;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.IViewBindCallback;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.properties.SpecialProperties;
import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/3/1 2:31 PM
 * <p>
 */
public final class TransformHelper {

    public static void bindGeneralProperties(View targetView, VirtualView virtualView) {

        ViewGroup.MarginLayoutParams layoutParams = MeasureHelp.transMeasure(virtualView);

        LocationHelp.transLocation(layoutParams, virtualView, targetView);

        targetView.setLayoutParams(layoutParams);

        targetView.setBackgroundColor(DyvHelper.parseColor(virtualView.bgColor));
    }

    public static void bindSpecialProperties(View targetView, VirtualView virtualView, Data data) {

        bindSpecialProperties(targetView, virtualView, data, null);
    }

    public static void bindSpecialProperties(View targetView, VirtualView virtualView, Data data, IViewBindCallback bindCallback) {

        try {

            Class<?> clz = virtualView.getClass();

            if (clz.isAnnotationPresent(SpecialProperties.class)) {

                SpecialProperties properties = clz.getAnnotation(SpecialProperties.class);

                if (properties != null) {

                    String propertyKey = properties.key();
                    String methodName = properties.method();

                    if (bindCallback != null) {

                        if (bindCallback.viewBind(targetView, methodName, data.get(propertyKey))) {
                            return;
                        }
                    }

                    _setSpecialProperties(targetView, methodName, data.get(propertyKey));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void _setSpecialProperties(View targetView,
                                              String methodName, String propertyValue) {

        if (TextUtils.isEmpty(methodName)) return;
        if (TextUtils.isEmpty(propertyValue)) return;

        if (TextUtils.equals(methodName, DyvConstant.VIEW_METHOD_SET_TEXT)) {

            ((TextView) targetView).setText(propertyValue);
        } else if (TextUtils.equals(methodName, DyvConstant.VIEW_METHOD_SET_IMAGE)) {

            ImageView imageView = (ImageView) targetView;

            int imgBindType = DyvHelper.isURLValue(propertyValue) ?
                    DyvConstant.IMAGE_BIND_TYPE_URL : DyvHelper.isColorValue(propertyValue) ?
                    DyvConstant.IMAGE_BIND_TYPE_COLOR : DyvConstant.IMAGE_BIND_TYPE_DRAWABLE;

            switch (imgBindType) {

                case DyvConstant.IMAGE_BIND_TYPE_URL:
                    // just ignore
                    break;

                case DyvConstant.IMAGE_BIND_TYPE_COLOR:

                    int color = DyvHelper.parseColor(propertyValue);

                    if (color != 0) {
                        imageView.setImageDrawable(new ColorDrawable(color));
                    }
                    break;

                case DyvConstant.IMAGE_BIND_TYPE_DRAWABLE:

                    Context context = imageView.getContext();
                    int drawableId = DyvHelper.parseDrawableID(context, propertyValue);

                    if (drawableId != 0) {
                        imageView.setImageDrawable(imageView.getContext().getDrawable(drawableId));
                    }

                    break;
            }
        }
    }

}
