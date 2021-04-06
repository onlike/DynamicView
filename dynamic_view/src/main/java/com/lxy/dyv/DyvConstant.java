package com.lxy.dyv;

/**
 * author: lxy
 * created on: 2021/2/19 6:04 PM
 * <p>
 */
public final class DyvConstant {


    public static int sDEVICE_WIDTH     = 0;

    public static int sDEVICE_HEIGHT    = 0;


    public static final int DEF_VIEW_TREE_CACHE_SIZE    = 5;

    public static final int DEF_DATA_TREE_SIZE          = 7;

    public static final int DEF_EVENT_TREE_SIZE         = DEF_DATA_TREE_SIZE;


    public static final int VIEW_SIZE_WRAP      = -1;

    public static final int VIEW_SIZE_MATCH     = -2;


    public static final int VIEW_TYPE_TEXT      = 1001;

    public static final int VIEW_TYPE_IMAGE     = 1002;

    public static final int VIEW_TYPE_FRAME     = 1003;

    public static final int VIEW_TYPE_LINEAR    = 1004;


    public static final int VIEW_GRAVITY_TOP                = 3101;

    public static final int VIEW_GRAVITY_LEFT               = 3102;

    public static final int VIEW_GRAVITY_BOTTOM             = 3103;

    public static final int VIEW_GRAVITY_RIGHT              = 3104;

    public static final int VIEW_GRAVITY_CENTER             = 3105;

    public static final int VIEW_GRAVITY_CENTER_VERTICAL    = 3106;

    public static final int VIEW_GRAVITY_CENTER_HORIZONTAL  = 3107;


    public static final int VIEW_ORIENTATION_VERTICAL       = 3201;

    public static final int VIEW_ORIENTATION_HORIZONTAL     = 3202;


    public static final int VIEW_IMAGE_SCALE_CENTER         = 3301;

    public static final int VIEW_IMAGE_SCALE_FIT            = 3302;


    public static final int VIEW_PARENT_TYPE_FRAME      = VIEW_TYPE_FRAME;

    public static final int VIEW_PARENT_TYPE_LINEAR     = VIEW_TYPE_LINEAR;


    public static final String VIEW_METHOD_SET_TEXT     = "setText";

    public static final String VIEW_METHOD_SET_IMAGE    = "setImage";


    public static final int PARSE_TYPE_VIEW     = 5001;

    public static final int PARSE_TYPE_DATA     = 5002;

    public static final int PARSE_TYPE_EVENT    = 5003;


    public static final int NATIVE_VIEW_TYPE_TEXT   = VIEW_TYPE_TEXT;

    public static final int NATIVE_VIEW_TYPE_IMAGE  = VIEW_TYPE_IMAGE;

    public static final int NATIVE_VIEW_TYPE_LINEAR = VIEW_TYPE_FRAME;

    public static final int NATIVE_VIEW_TYPE_FRAME  = VIEW_TYPE_LINEAR;


    public static final int IMAGE_BIND_TYPE_URL         = 9001;

    public static final int IMAGE_BIND_TYPE_COLOR       = 9002;

    public static final int IMAGE_BIND_TYPE_DRAWABLE    = 9003;


}
