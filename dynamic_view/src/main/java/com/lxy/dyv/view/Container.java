package com.lxy.dyv.view;

import java.util.ArrayList;

/**
 * author: lxy
 * created on: 2021/3/2 7:02 PM
 * <p>
 */
public abstract class Container extends VirtualView{

    public ArrayList<VirtualView> childList = new ArrayList<>();

    public void addChild(VirtualView child){

        if (child != null) {

            childList.add(child);
        } else {
            // to be print
        }
    }
}
