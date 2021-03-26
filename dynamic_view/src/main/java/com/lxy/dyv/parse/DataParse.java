package com.lxy.dyv.parse;

import com.lxy.dyv.data.Data;
import com.lxy.dyv.data.DefData;
import com.lxy.dyv.tree.DataTree;

import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/20 3:47 PM
 * <p>
 */
public class DataParse extends Parse {

    DataTree dataTree;

    boolean alreadyParse = false;

    public DataParse(Map<String, Object> sourceData) {
        super(sourceData);

        dataTree = new DataTree(sourceData.keySet().size());
    }

    @Override
    public Object parse() {
        // return

        if (!alreadyParse) {
            _parse();

            alreadyParse = true;
        }

        return dataTree;
    }

    private void _parse() {

        Data data;

        for (String viewTag : sourceData.keySet()) {

            data = new DefData((Map<String, String>) sourceData.get(viewTag), viewTag);

            dataTree.put(viewTag, data);
        }
    }
}
