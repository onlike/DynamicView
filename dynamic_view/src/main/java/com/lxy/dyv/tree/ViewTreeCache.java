package com.lxy.dyv.tree;

import android.support.annotation.Nullable;

import java.util.HashMap;

import static com.lxy.dyv.DyvConstant.DEF_VIEW_TREE_CACHE_SIZE;

/**
 * author: lxy
 * created on: 2021/2/19 6:31 PM
 * <p>
 */
public class ViewTreeCache {

    private static HashMap<String, ViewTree> vtCache;

    public ViewTreeCache(int initialCapacity) {
        vtCache = new HashMap<>(initialCapacity < 0 ? DEF_VIEW_TREE_CACHE_SIZE: initialCapacity);
    }

    public boolean hasCache(String treeTag) {
        if (vtCache == null) return false;

        return vtCache.containsKey(treeTag);
    }

    @Nullable
    public ViewTree get(String treeTag) {
        if (vtCache == null) return null;

        return vtCache.get(treeTag);
    }

    public void put(String treeTag, ViewTree viewTree) {
        if (vtCache == null) {
            vtCache = new HashMap<>(DEF_VIEW_TREE_CACHE_SIZE);
        }

        vtCache.put(treeTag, viewTree);
    }


}
