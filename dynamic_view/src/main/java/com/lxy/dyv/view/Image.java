package com.lxy.dyv.view;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.properties.GeneralProperties;
import com.lxy.dyv.properties.SpecialProperties;

/**
 * author: lxy
 * created on: 2021/2/22 3:31 PM
 * <p>
 */

@SpecialProperties(key = "imgSource", method = DyvConstant.VIEW_METHOD_SET_IMAGE)
public class Image extends VirtualView{

    @GeneralProperties(key = "scaleType")
    public int scaleType;

}
