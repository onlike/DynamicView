package com.lxy.dyv.transform;

import android.text.TextUtils;

import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/3/3 3:29 PM
 * <p>
 */
public class TransformRecord {

    String viewTag;

    VTransform transform;

    VirtualView virtualView;

    public TransformRecord(String viewTag, VTransform transform, VirtualView virtualView) {
        this.viewTag = viewTag;
        this.transform = transform;
        this.virtualView = virtualView;
    }

    public void injectVirtualView() {
        transform.injectVirtualView(virtualView);
    }

    public boolean isTarget(String viewTag) {
        return TextUtils.equals(this.viewTag, viewTag);
    }

    public boolean isContainer(){
        return transform instanceof ContainerTransform;
    }
}
