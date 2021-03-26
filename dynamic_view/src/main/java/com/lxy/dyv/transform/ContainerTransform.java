package com.lxy.dyv.transform;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.dyv.DyvHelper;

import java.util.ArrayList;

/**
 * author: lxy
 * created on: 2021/2/20 3:45 PM
 * <p>
 */
public abstract class ContainerTransform extends VTransform {

    ArrayList<VTransform> childList = new ArrayList<>();

    boolean childInject = false;

    public ContainerTransform(Context mCtx) {
        super(mCtx);
    }

    @Override
    View obtainView() {

        ViewGroup viewGroup = obtainContainerView();

        if (!childInject && !DyvHelper.isNull(viewGroup)) {
            childInject(viewGroup);

            childInject = true;
        }

        return viewGroup;
    }

    public void addChild(VTransform transform) {
        childList.add(transform);
    }

    public void childInject(ViewGroup container) {

        for (VTransform transform : childList) {

            if (transform instanceof ContainerTransform) {

                ViewGroup child_Container = ((ContainerTransform) transform).obtainContainerView();

                if (!DyvHelper.isNull(child_Container)) {

                    ContainerTransform child_ContainerTransform = (ContainerTransform) transform;

                    child_ContainerTransform.childInject(child_Container);

                    container.addView(child_Container);
                }


            } else {

                View child = transform.obtainView();

                if (!DyvHelper.isNull(child)) {

                    container.addView(child);
                }
            }
        }
    }

    abstract ViewGroup obtainContainerView();
}
