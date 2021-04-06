package com.lxy.dyv.transform;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.NativeViewCreator;
import com.lxy.dyv.transform.help.TransformHelper;
import com.lxy.dyv.view.LinearContainer;

/**
 * author: lxy
 * created on: 2021/3/2 7:34 PM
 * <p>
 */
public class LinearTransform extends ContainerTransform{

    LinearLayout linearLayout;

    public LinearTransform(Context mCtx) {
        super(mCtx);
    }

    @Override
    void createView() {
        linearLayout = NativeViewCreator.createNativeView(DyvConstant.NATIVE_VIEW_TYPE_LINEAR, mCtx);
    }

    @Override
    void bindView() {

        if (DyvHelper.isNull(linearLayout)) return;

        TransformHelper.bindGeneralProperties(linearLayout, virtualView);

        if (virtualView instanceof LinearContainer) {

            int orientation = ((LinearContainer) virtualView).orientation;

            switch (orientation){

                case DyvConstant.VIEW_ORIENTATION_VERTICAL:

                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    break;

                case DyvConstant.VIEW_ORIENTATION_HORIZONTAL:

                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    break;
            }
        }
    }

    @Override
    void bindClick() {

        if (DyvHelper.isNull(linearLayout)) return;

        if (DyvHelper.isNull(viewEvent)) return;

        if (!viewEvent.legal()) return;

        linearLayout.setOnClickListener(this);
    }

    @Override
    void bindData() {
    }

    @Override
    ViewGroup obtainContainerView() {
        return linearLayout;
    }
}
