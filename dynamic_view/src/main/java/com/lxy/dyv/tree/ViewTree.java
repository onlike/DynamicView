package com.lxy.dyv.tree;

import com.lxy.dyv.view.VirtualView;

/**
 * author: lxy
 * created on: 2021/2/19 6:31 PM
 * <p>
 */
public class ViewTree {

    String treeTag;

    VirtualView rootView;

    public ViewTree(String treeTag, VirtualView rootView) {
        this.treeTag = treeTag;
        this.rootView = rootView;
    }

    public VirtualView rootView(){
        return rootView;
    }
}
