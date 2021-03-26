package com.lxy.dyv.tree;

import android.support.annotation.Nullable;

import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;

import java.util.HashMap;

/**
 * author: lxy
 * created on: 2021/2/23 4:31 PM
 * <p>
 */
public class EventTree {

    // key是tag event是每个view的事件
    HashMap<String, Event> eventMap;

    public EventTree(int initialCapacity) {
        eventMap = new HashMap<>(initialCapacity);
    }

    public void put(String viewTag, Event event) {
        eventMap.put(viewTag, event);
    }

    @Nullable
    public Event get(String viewTag){
        if (DyvHelper.isNull(eventMap)) return null;

        return eventMap.get(viewTag);
    }
}
