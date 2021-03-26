package com.lxy.dyv.transform;

import android.content.Context;

import com.lxy.dyv.view.Container;
import com.lxy.dyv.view.Image;
import com.lxy.dyv.view.LinearContainer;
import com.lxy.dyv.view.VirtualView;

import java.util.List;

/**
 * author: lxy
 * created on: 2021/3/2 7:21 PM
 * <p>
 */
final class TransformCreator {

    interface OnCreateCallback {
        void onCreate(String viewTag, VTransform transform, VirtualView origin);
    }

    public static VTransform createTransform(VirtualView virtualView, Context context, final OnCreateCallback callback) {

        return _createTransform(virtualView, context, new OnCreateCallback() {
            @Override
            public void onCreate(String viewTag, VTransform transform, VirtualView origin) {
                callback.onCreate(viewTag, transform, origin);
            }
        });
    }

    private static VTransform _createTransform(VirtualView virtualView, Context context, OnCreateCallback callback) {

        VTransform transform = null;

        if (virtualView instanceof Container) {

            if (virtualView instanceof LinearContainer) {

                transform = new LinearTransform(context);
            } else {

                transform = new FrameTransform(context);
            }

            callback.onCreate(virtualView.tag, transform, virtualView);

            List<VirtualView> childList = ((Container) virtualView).childList;

            if (childList != null) {

                ContainerTransform parentTransform = (ContainerTransform) transform;

                for (VirtualView view : childList) {

                    VTransform childTransform = _createTransform(view, context, callback);

                    parentTransform.addChild(childTransform);
                }
            }
        } else {

            if (virtualView instanceof Image) {

                transform = new ImageTransform(context);
            } else {

                transform = new TextTransform(context);
            }

            callback.onCreate(virtualView.tag, transform, virtualView);
        }

        return transform;

    }


}
