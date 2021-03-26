package com.lxy.dyv.view;

import com.lxy.dyv.properties.GeneralProperties;

/**
 * author: lxy
 * created on: 2021/2/22 3:31 PM
 * <p>
 */
public class LinearContainer extends Container{


    /**
     * 线性排布方向
     *
     * {@link com.lxy.dyv.DyvConstant#VIEW_ORIENTATION_VERTICAL}
     * {@link com.lxy.dyv.DyvConstant#VIEW_ORIENTATION_HORIZONTAL}
     */

    @GeneralProperties(key = "orientation")
    public int orientation;

}
