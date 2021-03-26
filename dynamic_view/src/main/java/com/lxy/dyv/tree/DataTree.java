package com.lxy.dyv.tree;

import android.support.annotation.Nullable;

import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.data.Data;

import java.util.HashMap;

/**
 * author: lxy
 * created on: 2021/2/23 4:38 PM
 * <p>
 */
public class DataTree {

    // key是tag value是每个view的数据
    HashMap<String, Data> dataMap;

    public DataTree(int initialCapacity) {
        dataMap = new HashMap<>(initialCapacity);
    }

    public void put(String viewTag, Data data) {
        dataMap.put(viewTag, data);
    }

    @Nullable
    public Data get(String viewTag){
        if (DyvHelper.isNull(dataMap)) return null;

        return  dataMap.get(viewTag);
    }
}
