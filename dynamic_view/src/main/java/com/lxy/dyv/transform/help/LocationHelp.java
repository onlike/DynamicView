package com.lxy.dyv.transform.help;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/2/19 6:49 PM
 * <p>
 */
public final class LocationHelp {


    protected static void transLocation(ViewGroup.MarginLayoutParams layoutParams, VirtualView virtualView, View targetView) {
        _setLayoutGravityProperties(layoutParams, virtualView);

        _setMarginProperties(layoutParams, virtualView);

        _setPaddingProperties(targetView, virtualView);

        _setSpecialViewGravity(targetView, virtualView);
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

    private static void _setSpecialViewGravity(View targetView, VirtualView virtualView) {

        if (targetView instanceof TextView) {
            ((TextView) targetView).setGravity(__obtainRealGravity(virtualView.selfGravity));
        }

        if (targetView instanceof LinearLayout) {
            ((LinearLayout) targetView).setGravity(__obtainRealGravity(virtualView.selfGravity));
        }
    }

}
