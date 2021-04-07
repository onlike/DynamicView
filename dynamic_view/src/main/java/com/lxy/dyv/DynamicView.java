package com.lxy.dyv;

import android.content.Context;
import android.view.View;

import com.lxy.dyv.parse.DataParse;
import com.lxy.dyv.parse.EventParse;
import com.lxy.dyv.parse.ViewParse;
import com.lxy.dyv.transform.VirtualViewTransform;
import com.lxy.dyv.tree.DataTree;
import com.lxy.dyv.tree.EventTree;
import com.lxy.dyv.tree.ViewTree;
import com.lxy.dyv.tree.ViewTreeCache;

import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/19 6:02 PM
 * <p>
 * 持有ViewTree、持有数据、持有回调接口
 */
public final class DynamicView {

    ViewTreeCache treeCache;

    Context mCtx;


    Map<String, Object> viewData;

    Map<String, Object> propertiesData;


    ViewTree viewTree;

    EventTree eventTree;

    DataTree dataTree;


    // 最终落实翻译
    VirtualViewTransform viewTransform;

    private DynamicView(Builder builder) {

        this.treeCache = builder.treeCache;
        this.mCtx = builder.mCtx;
        this.viewData = builder.viewData;
        this.propertiesData = builder.propertiesData;

        this.viewTransform = new VirtualViewTransform(mCtx);

        parse();

        viewTransform.injectViewTree(viewTree);
    }

    private DynamicView(ViewTreeCache treeCache, Map<String, Object> viewData) {
        this.treeCache = treeCache;
        this.viewData = viewData;

        _parseViewTree();
    }

    public View bindView() {
        return viewTransform.obtainRootView();
    }

    public void bindData() {
        bindData(null);
    }

    public void bindData(IViewBindCallback bindCallback) {

        viewTransform.injectImageBindCallback(bindCallback);

        viewTransform.injectDataTree(dataTree);

        viewTransform.injectEventTree(eventTree);
    }

    public void bindEventTouch(IEventTouchCallback eventTouchCallback) {

        viewTransform.injectEventTouchCallback(eventTouchCallback);
    }

    public void recycler() {

    }

    private void parse() {
        _parseViewTree();
        _parseDataTree();
        _parseEventTree();
    }

    private void _parseViewTree() {

        ViewParse vp = new ViewParse(viewData);

        String treeTag = vp.treeTag();

        if (treeCache.hasCache(treeTag)) {

            viewTree = treeCache.get(treeTag);
        } else {

            viewTree = (ViewTree) vp.parse();

            treeCache.put(treeTag, viewTree);
        }
    }

    private void _parseDataTree() {

        dataTree = (DataTree) new DataParse(DyvHelper.getMap(propertiesData, "data")).parse();
    }

    private void _parseEventTree() {

        eventTree = (EventTree) new EventParse(DyvHelper.getMap(propertiesData, "event")).parse();
    }

    public static class Builder {

        ViewTreeCache treeCache;

        Context mCtx;

        Map<String, Object> viewData;

        Map<String, Object> propertiesData;

        public Builder(ViewTreeCache treeCache) {
            this.treeCache = treeCache;
        }


        public Builder injectViewData(Map<String, Object> viewData) {
            this.viewData = viewData;
            return this;
        }

        public Builder injectPropertiesData(Map<String, Object> propertiesData) {
            this.propertiesData = propertiesData;
            return this;
        }

        public DynamicView build() {
            return new DynamicView(this);
        }

    }

    public static class PreloadBuilder{

        ViewTreeCache treeCache;

        public PreloadBuilder(ViewTreeCache treeCache) {
            this.treeCache = treeCache;
        }

        public void injectViewTemplate(Map<String, Object> viewData){
            new DynamicView(treeCache, viewData);
        }
    }
}
