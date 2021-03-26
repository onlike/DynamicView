package com.lxy.dyv.view;


import com.lxy.dyv.properties.GeneralProperties;

/**
 * author: lxy
 * created on: 2021/2/19 6:03 PM
 * <p>
 */
public abstract class VirtualView {

    // 各VirtualView负责具体自身的属性的存储
    // 属性由ViewParse解析的时候注入
    @GeneralProperties(key = "tag")
    public String tag;

    @GeneralProperties(key = "type")
    public int type;


    @GeneralProperties(key = "width")
    public double width;

    @GeneralProperties(key = "height")
    public double height;


    // 自身的gravity 类似TextView.setGravity
    @GeneralProperties(key = "selfGravity")
    public int selfGravity;

    // 位于布局中的gravity
    @GeneralProperties(key = "layoutGravity")
    public int layoutGravity;


    @GeneralProperties(key = "topMargin")
    public double topMargin;

    @GeneralProperties(key = "leftMargin")
    public double leftMargin;

    @GeneralProperties(key = "rightMargin")
    public double rightMargin;

    @GeneralProperties(key = "bottomMargin")
    public double bottomMargin;


    @GeneralProperties(key = "topPadding")
    public double topPadding;

    @GeneralProperties(key = "leftPadding")
    public double leftPadding;

    @GeneralProperties(key = "rightPadding")
    public double rightPadding;

    @GeneralProperties(key = "bottomPadding")
    public double bottomPadding;


    @GeneralProperties(key = "bgColor")
    public String bgColor;


    /**
     * 父布局类型
     * {@link com.lxy.dyv.DyvConstant#VIEW_PARENT_TYPE_LINEAR}
     * {@link com.lxy.dyv.DyvConstant#VIEW_PARENT_TYPE_FRAME}
     */
    @GeneralProperties(key = "parentType")
    public int parentType;
}
