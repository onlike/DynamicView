package com.lxy.dyv.transform.help;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.dyv.DynamicMaster;
import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.properties.SpecialProperties;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/3/1 2:31 PM
 * <p>
 */
public final class TransformHelper {

    public static void bindGeneralProperties(View targetView, VirtualView virtualView) {

        ViewGroup.MarginLayoutParams layoutParams = _createLayoutParams(virtualView);

        _setLayoutGravityProperties(layoutParams, virtualView);

        _setMarginProperties(layoutParams, virtualView);

        _setPaddingProperties(targetView, virtualView);

        targetView.setLayoutParams(layoutParams);

        targetView.setBackgroundColor(DyvHelper.parseColor(virtualView.bgColor));

        if (targetView instanceof TextView) {
            ((TextView) targetView).setGravity(__obtainRealGravity(virtualView.selfGravity));
        }

        if (targetView instanceof LinearLayout) {
            ((LinearLayout) targetView).setGravity(__obtainRealGravity(virtualView.selfGravity));
        }
    }

    public static void bindSpecialProperties(View targetView, VirtualView virtualView, Data data) {

        try {

            Class<?> clz = virtualView.getClass();

            if (clz.isAnnotationPresent(SpecialProperties.class)) {

                SpecialProperties properties = clz.getAnnotation(SpecialProperties.class);

                if (properties != null) {

                    String propertyKey = properties.key();
                    String methodName = properties.method();

                    _setSpecialProperties(targetView, methodName, data.get(propertyKey));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static


    private static ViewGroup.MarginLayoutParams _createLayoutParams(VirtualView virtualView) {

        int width = __obtainRealSize(virtualView.width, DyvHelper.getDeviceWidth());

        int height = __obtainRealSize(virtualView.height, DyvHelper.getDeviceHeight());

        ViewGroup.MarginLayoutParams layoutParams;

        if (virtualView.parentType == DyvConstant.VIEW_PARENT_TYPE_LINEAR) {

            layoutParams = new LinearLayout.LayoutParams(width, height);
        } else if (virtualView.parentType == DyvConstant.VIEW_PARENT_TYPE_FRAME) {

            layoutParams = new FrameLayout.LayoutParams(width, height);
        } else {

            layoutParams = new ViewGroup.MarginLayoutParams(width, height);
        }

        return layoutParams;
    }

    private static void _setLayoutGravityProperties(ViewGroup.MarginLayoutParams layoutParams, VirtualView virtualView) {

        if (layoutParams instanceof LinearLayout.LayoutParams) {

            ((LinearLayout.LayoutParams) layoutParams).gravity = __obtainRealGravity(virtualView.layoutGravity);
        } else if (layoutParams instanceof FrameLayout.LayoutParams) {

            ((FrameLayout.LayoutParams) layoutParams).gravity = __obtainRealGravity(virtualView.layoutGravity);
        } else {
            // just ignore
        }
    }

    private static void _setMarginProperties(ViewGroup.MarginLayoutParams layoutParams, VirtualView virtualView) {

        layoutParams.topMargin = DyvHelper.percent2Real(virtualView.topMargin, DyvHelper.getDeviceHeight());

        layoutParams.leftMargin = DyvHelper.percent2Real(virtualView.leftMargin, DyvHelper.getDeviceWidth());

        layoutParams.bottomMargin = DyvHelper.percent2Real(virtualView.bottomMargin, DyvHelper.getDeviceHeight());

        layoutParams.rightMargin = DyvHelper.percent2Real(virtualView.rightMargin, DyvHelper.getDeviceWidth());
    }


    private static void _setPaddingProperties(View targetView, VirtualView virtualView) {

        int pTop = DyvHelper.percent2Real(virtualView.topPadding, DyvHelper.getDeviceHeight());

        int pLeft = DyvHelper.percent2Real(virtualView.leftPadding, DyvHelper.getDeviceWidth());

        int pBottom = DyvHelper.percent2Real(virtualView.bottomPadding, DyvHelper.getDeviceHeight());

        int pRight = DyvHelper.percent2Real(virtualView.rightPadding, DyvHelper.getDeviceWidth());

        targetView.setPadding(pLeft, pTop, pRight, pBottom);
    }

    private static int __obtainRealSize(double size, int screenSize) {

        int result = ViewGroup.LayoutParams.WRAP_CONTENT;

        if (size > 0) {

            result = DyvHelper.percent2Real(Math.abs(size), screenSize);
        } else {

            int iSize = (int) size;

            switch (iSize) {
                case DyvConstant.VIEW_SIZE_WRAP:

                    result = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;

                case DyvConstant.VIEW_SIZE_MATCH:

                    result = ViewGroup.LayoutParams.MATCH_PARENT;
                    break;
            }
        }
        return result;
    }

    private static int __obtainRealGravity(int gravity) {
        int result = Gravity.NO_GRAVITY;

        if (!DyvHelper.propertiesLegal(gravity)) return result;

        switch (gravity) {

            case DyvConstant.VIEW_GRAVITY_TOP:

                result = Gravity.TOP;
                break;

            case DyvConstant.VIEW_GRAVITY_LEFT:

                result = Gravity.LEFT;
                break;

            case DyvConstant.VIEW_GRAVITY_BOTTOM:

                result = Gravity.BOTTOM;
                break;

            case DyvConstant.VIEW_GRAVITY_RIGHT:

                result = Gravity.RIGHT;
                break;

            case DyvConstant.VIEW_GRAVITY_CENTER:

                result = Gravity.CENTER;
                break;

            case DyvConstant.VIEW_GRAVITY_CENTER_VERTICAL:

                result = Gravity.CENTER_VERTICAL;
                break;

            case DyvConstant.VIEW_GRAVITY_CENTER_HORIZONTAL:

                result = Gravity.CENTER_HORIZONTAL;
                break;
        }

        return result;
    }

    private static void _setSpecialProperties(View targetView,
                                              String methodName, String propertyValue) {

        if (TextUtils.isEmpty(methodName)) return;
        if (TextUtils.isEmpty(propertyValue)) return;

        if (TextUtils.equals(methodName, DyvConstant.VIEW_METHOD_SET_TEXT)) {

            ((TextView) targetView).setText(propertyValue);
        } else {


            ImageView imageView = (ImageView) targetView;


            int bindType = DyvHelper.isURLValue(propertyValue) ?
                    DyvConstant.IMAGE_BIND_TYPE_URL : DyvHelper.isColorValue(propertyValue) ?
                    DyvConstant.IMAGE_BIND_TYPE_COLOR : DyvConstant.IMAGE_BIND_TYPE_DRAWABLE;


            if (DynamicMaster.imageBindListener.imageBind(imageView, propertyValue, bindType))
                return;


            switch (bindType) {

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
