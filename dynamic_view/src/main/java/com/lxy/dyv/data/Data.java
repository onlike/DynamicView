package com.lxy.dyv.data;


import android.support.annotation.Nullable;

import com.lxy.dyv.DyvHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/22 7:34 PM
 * <p>
 */
public abstract class Data {

    Map<String, String> dataMap;

    String viewTag;

    public Data(Map<String, String> dataMap, String viewTag) {
        this.dataMap = dataMap;
        this.viewTag = viewTag;
    }

    @Nullable
    public String get(String dataKey) {
        if (DyvHelper.isNull(dataMap)) return null;

        return dataMap.get(dataKey);
    }

    @Nullable
    public Map<String, String> origin(){
        return dataMap;
    }
}
