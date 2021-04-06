package com.lxy.dyv.transform;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.IViewBindCallback;
import com.lxy.dyv.NativeViewCreator;
import com.lxy.dyv.transform.help.TransformHelper;
import com.lxy.dyv.view.Image;

/**
 * author: lxy
 * created on: 2021/2/20 3:45 PM
 * <p>
 */
public class ImageTransform extends VTransform {

    ImageView imageView;

    public ImageTransform(Context mCtx) {
        super(mCtx);
    }

    @Override
    void createView() {
        imageView = NativeViewCreator.createNativeView(DyvConstant.NATIVE_VIEW_TYPE_IMAGE, mCtx);
    }

    @Override
    void bindView() {

        if (DyvHelper.isNull(imageView)) return;

        TransformHelper.bindGeneralProperties(imageView, virtualView);

        if (virtualView instanceof Image) {

            int st = ((Image) virtualView).scaleType;

            ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

            switch (st) {

                case DyvConstant.VIEW_IMAGE_SCALE_CENTER:

                    scaleType = ImageView.ScaleType.CENTER_CROP;
                    break;

                case DyvConstant.VIEW_IMAGE_SCALE_FIT:

                    scaleType = ImageView.ScaleType.FIT_XY;
                    break;
            }

            imageView.setScaleType(scaleType);
        }
    }

    @Override
    void bindClick() {

        if (DyvHelper.isNull(imageView)) return;

        if (DyvHelper.isNull(viewEvent)) return;

        if (!viewEvent.legal()) return;

        imageView.setOnClickListener(this);
    }

    @Override
    void bindData() {

        if (DyvHelper.isNull(imageView)) return;

        if (DyvHelper.isNull(virtualView)) return;

        if (virtualView instanceof Image) {
            TransformHelper.bindSpecialProperties(imageView, virtualView, viewData, viewBindCallback);
        }
    }

    @Override
    View obtainView() {
        return imageView;
    }
}
