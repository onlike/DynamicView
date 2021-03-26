package com.lxy.dyv;

import android.support.annotation.Nullable;
import android.view.View;

import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;

/**
 * author: lxy
 * created on: 2021/2/19 6:36 PM
 * <p>
 *     事件触发后的回调
 */
public interface IEventTouchCallback {

    void onEventTouch(View view, Event event, @Nullable Data data);


}
