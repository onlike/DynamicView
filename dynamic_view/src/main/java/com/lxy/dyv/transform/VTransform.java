package com.lxy.dyv.transform;

import android.content.Context;
import android.view.View;

import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.IEventTouchCallback;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;
import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/2/19 6:01 PM
 * <p>
 *     Transform负责实际View的操纵，包括属性绑定，数据绑定，事件绑定
 */
abstract class VTransform implements View.OnClickListener{

    Context mCtx;

    VirtualView virtualView;

    Data viewData;

    Event viewEvent;

    IEventTouchCallback eventTouchCallback;

    public VTransform(Context mCtx) {
        this.mCtx = mCtx;
    }

    public void injectVirtualView(VirtualView virtualView){
        this.virtualView = virtualView;

        createView();

        bindView();
    }

    public void injectViewData(Data viewData){
        this.viewData = viewData;

        bindData();
    }

    public void injectViewEvent(Event viewEvent){
        this.viewEvent = viewEvent;

        bindClick();
    }

    public void injectEventTouchCallback(IEventTouchCallback eventTouchCallback){
        this.eventTouchCallback = eventTouchCallback;
    }

    @Override
    public void onClick(View v) {
        if (DyvHelper.isNull(eventTouchCallback)) return;

        eventTouchCallback.onEventTouch(v, viewEvent, viewData);
    }

    // 生成View
    abstract void createView();

    // 绑定基础属性
    abstract void bindView();

    // 绑定Event事件
    abstract void bindClick();

    // 绑定数据属性
    abstract void bindData();

    // 获取配置完毕的View
    abstract View obtainView();





}
