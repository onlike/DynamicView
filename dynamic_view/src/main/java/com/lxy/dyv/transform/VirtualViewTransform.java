package com.lxy.dyv.transform;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.IEventTouchCallback;
import com.lxy.dyv.IViewBindCallback;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;
import com.lxy.dyv.tree.DataTree;
import com.lxy.dyv.tree.EventTree;
import com.lxy.dyv.tree.ViewTree;
import com.lxy.dyv.view.VirtualView;

import java.util.HashMap;

/**
 * author: lxy
 * created on: 2021/2/19 6:02 PM
 * <p>
 */
public final class VirtualViewTransform implements TransformCreator.OnCreateCallback {

    Context mCtx;

    ViewTree viewTree;

    EventTree eventTree;

    DataTree dataTree;

    IEventTouchCallback mTouchCallback;

    IViewBindCallback mIViewBindCallback;

    HashMap<String, TransformRecord> transformRecord;

    public VirtualViewTransform(Context mCtx) {
        this.mCtx = mCtx;

        this.transformRecord = new HashMap<>(10);
    }

    public void injectViewTree(@NonNull ViewTree viewTree) {
        this.viewTree = viewTree;

        TransformCreator.createTransform(viewTree.rootView(), mCtx, this);

        for (TransformRecord value : transformRecord.values()) {
            value.injectVirtualView();
        }
    }

    public void injectDataTree(@NonNull DataTree dataTree) {
        this.dataTree = dataTree;

        for (TransformRecord value : transformRecord.values()) {

            Data data = dataTree.get(value.viewTag);

            if (!DyvHelper.isNull(data)) {

                value.transform.injectViewData(data);
            }
        }
    }

    public void injectEventTree(@NonNull EventTree eventTree) {
        this.eventTree = eventTree;

        for (TransformRecord value : transformRecord.values()) {

            Event event = eventTree.get(value.viewTag);

            if (!DyvHelper.isNull(event)) {

                value.transform.injectViewEvent(event);
            }
        }
    }

    public void injectImageBindCallback(IViewBindCallback bindCallback) {
        this.mIViewBindCallback = bindCallback;

        for (TransformRecord value : transformRecord.values()) {

            if (value.transform instanceof ImageTransform) {

                value.transform.injectViewBindCallback(bindCallback);
            }
        }
    }

    public void injectEventTouchCallback(IEventTouchCallback callback) {
        this.mTouchCallback = callback;

        for (TransformRecord value : transformRecord.values()) {
            value.transform.injectEventTouchCallback(callback);
        }
    }

    public View obtainRootView() {

        String rootViewTag = viewTree.rootView().tag;

        TransformRecord tr = transformRecord.get(rootViewTag);

        if (DyvHelper.isNull(tr)) {
            throw new IllegalArgumentException("origin args illegal");
        }

        return tr.transform.obtainView();

    }

    @Override
    public void onCreate(String viewTag, VTransform transform, VirtualView origin) {
        transformRecord.put(viewTag, new TransformRecord(viewTag, transform, origin));
    }

}
