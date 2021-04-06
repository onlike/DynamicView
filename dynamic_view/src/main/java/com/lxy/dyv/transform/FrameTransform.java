package com.lxy.dyv.transform;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.NativeViewCreator;
import com.lxy.dyv.transform.help.TransformHelper;

/**
 * author: lxy
 * created on: 2021/3/2 7:30 PM
 * <p>
 */
public class FrameTransform extends ContainerTransform {

    FrameLayout frameLayout;

    public FrameTransform(Context mCtx) {
        super(mCtx);
    }

    @Override
    void createView() {
        frameLayout = NativeViewCreator.createNativeView(DyvConstant.NATIVE_VIEW_TYPE_FRAME, mCtx);
    }

    @Override
    void bindView() {
        if (DyvHelper.isNull(frameLayout)) return;

        TransformHelper.bindGeneralProperties(frameLayout, virtualView);
    }

    @Override
    void bindClick() {

        if (DyvHelper.isNull(frameLayout)) return;

        if (DyvHelper.isNull(viewEvent)) return;

        if (!viewEvent.legal()) return;

        frameLayout.setOnClickListener(this);
    }

    @Override
    void bindData() {
    }

    @Override
    ViewGroup obtainContainerView() {
        return frameLayout;
    }
}
