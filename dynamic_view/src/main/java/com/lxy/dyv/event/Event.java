package com.lxy.dyv.event;



import com.lxy.dyv.DyvHelper;

import java.util.List;

/**
 * author: lxy
 * created on: 2021/2/19 6:36 PM
 * <p>
 */
public abstract class Event {

    List<String> action;

    String viewTag;

    public Event(List<String> action, String viewTag) {
        this.action = action;
        this.viewTag = viewTag;
    }

    public boolean legal(){
        return DyvHelper.propertiesLegal(action);
    }

    public List<String> action() {
        return action;
    }

    public String viewTag() {
        return viewTag;
    }
}
