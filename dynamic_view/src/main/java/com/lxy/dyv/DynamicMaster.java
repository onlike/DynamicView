package com.lxy.dyv;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.lxy.dyv.tree.ViewTreeCache;

/**
 * author: lxy
 * created on: 2021/2/22 10:58 AM
 * <p>
 *     框架入口，负责获取虚拟View、转译虚拟View、绑定数据
 */
public final class DynamicMaster {

    private static volatile DynamicMaster sInstance;

    private Application mApplication;

    private ViewTreeCache treeCache;

    public static DynamicMaster init(Application application) {

        if (sInstance == null) {

            synchronized (DynamicMaster.class) {

                if (sInstance == null) {

                    sInstance = new DynamicMaster(application);
                }
            }
        }

        return sInstance;
    }

    public static DynamicView.Builder get(Context context) {
        if (sInstance == null) {
            throw new IllegalStateException("must call DynamicMaster.init before get");
        }

        DynamicView.Builder builder = sInstance.obtainBuilder(sInstance.treeCache);

        builder.mCtx = context;

        return builder;
    }

    private DynamicView.Builder obtainBuilder(ViewTreeCache treeCache){
        return new DynamicView.Builder(treeCache);
    }

    private DynamicMaster(Application application) {

        this.mApplication = application;

        this.treeCache = new ViewTreeCache(5);

        DyvHelper.obtainScreenSize(application);
    }

}
