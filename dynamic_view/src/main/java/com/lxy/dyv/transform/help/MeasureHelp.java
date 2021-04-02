package com.lxy.dyv.transform.help;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/2/19 6:47 PM
 * <p>
 */
public final class MeasureHelp {

    protected static ViewGroup.MarginLayoutParams transMeasure(VirtualView virtualView){
        return _createLayoutParams(virtualView);
    }

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
}
