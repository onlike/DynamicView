package com.lxy.dyv.view;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.properties.GeneralProperties;
import com.lxy.dyv.properties.SpecialProperties;

/**
 * author: lxy
 * created on: 2021/2/22 3:31 PM
 * <p>
 */

@SpecialProperties(key = "textContent", method = DyvConstant.VIEW_METHOD_SET_TEXT)
public class Text extends VirtualView {

    @GeneralProperties(key = "fontName")
    public String fontName;

    @GeneralProperties(key = "textColor")
    public String textColor;

    @GeneralProperties(key = "textSize")
    public int textSize;

    @GeneralProperties(key = "maxLines")
    public int maxLines;

}
