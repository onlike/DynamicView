package com.lxy.dyv.parse;

import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.VirtualViewCreator;
import com.lxy.dyv.tree.ViewTree;
import com.lxy.dyv.view.Container;
import com.lxy.dyv.view.VirtualView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/20 3:47 PM
 * <p>
 */
public class ViewParse extends Parse {

    private String treeTag;

    private Map<String, Object> treeData;


    boolean alreadyParse = false;


    private ViewTree viewTree;

    public ViewParse(Map<String, Object> sourceData) {
        super(sourceData);


        treeTag = DyvHelper.getString(sourceData, "treeTag");

        treeData = DyvHelper.getMap(sourceData, "viewTree");

    }

    @Override
    public Object parse() {

        if (!alreadyParse) {

            VirtualView rootView = obtainVirtualView(treeData);

            viewTree = new ViewTree(treeTag, rootView);
        }


        return viewTree;
    }

    public String treeTag() {

        return treeTag;

    }

    private VirtualView obtainVirtualView(Map<String, Object> treeData) {

        VirtualView virtualView = VirtualViewCreator.createVirtualView(
                DyvHelper.getInteger(treeData, "type"));

        if (virtualView == null) return null;

        VirtualViewCreator.injectProperties(virtualView, treeData);

        if (virtualView instanceof Container) {

            if (hasChild(treeData)) {

                for (Map<String, Object> childMap : getChildList(treeData)) {
                    ((Container) virtualView).addChild(obtainVirtualView(childMap));
                }
            }
        }

        return virtualView;
    }

    private boolean hasChild(Map<String, Object> treeData) {

        Object child = treeData.get("child");

        if (child == null) return false;

        return child instanceof List;
    }

    private ArrayList<Map<String, Object>> getChildList(Map<String, Object> treeData) {
        return (ArrayList<Map<String, Object>>) treeData.get("child");
    }


}
