package com.lxy.dyv;

import android.support.annotation.IntDef;
import android.widget.ImageView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: lxy
 * created on: 2021/3/2 3:44 PM
 * <p>
 */
public interface ImageBindListener {

    @IntDef({DyvConstant.IMAGE_BIND_TYPE_URL, DyvConstant.IMAGE_BIND_TYPE_COLOR, DyvConstant.IMAGE_BIND_TYPE_DRAWABLE})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    @interface BindType {
    }

    boolean imageBind(ImageView imageView, String value, @BindType int bindType);
}
