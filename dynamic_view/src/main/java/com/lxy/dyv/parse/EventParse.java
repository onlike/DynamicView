package com.lxy.dyv.parse;

import com.lxy.dyv.event.DefEvent;
import com.lxy.dyv.event.Event;
import com.lxy.dyv.tree.EventTree;

import java.util.List;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/20 3:48 PM
 * <p>
 */
public class EventParse extends Parse{

    EventTree eventTree;

    boolean alreadyParse = false;

    public EventParse(Map<String, Object> sourceData) {
        super(sourceData);

        eventTree = new EventTree(sourceData.keySet().size());
    }

    @Override
    public Object parse() {
        // return

        if (!alreadyParse) {
            _parse();

            alreadyParse = true;
        }

        return eventTree;
    }

    private void _parse(){

        Event event;

        for (String viewTag : sourceData.keySet()) {

            event = new DefEvent((List<String>) sourceData.get(viewTag), viewTag);

            eventTree.put(viewTag, event);
        }
    }
}
