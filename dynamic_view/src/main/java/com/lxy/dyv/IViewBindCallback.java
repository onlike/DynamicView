package com.lxy.dyv;

import android.support.annotation.IntDef;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: lxy
 * created on: 2021/3/2 3:44 PM
 * <p>
 */
public interface IViewBindCallback {

    @IntDef({DyvConstant.IMAGE_BIND_TYPE_URL, DyvConstant.IMAGE_BIND_TYPE_COLOR, DyvConstant.IMAGE_BIND_TYPE_DRAWABLE})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    @interface BindImageType {
    }

    /**
     *
     * @param targetView target
     * @param method to bind methodName {@link DyvConstant#VIEW_METHOD_SET_TEXT} {@link DyvConstant#VIEW_METHOD_SET_IMAGE}
     * @param value to bind properties
     * @return returning true means consuming the bind
     */
    boolean viewBind(View targetView, String method, String value);

}
