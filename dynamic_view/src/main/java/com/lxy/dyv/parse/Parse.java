package com.lxy.dyv.parse;

import android.support.annotation.IntDef;

import com.lxy.dyv.DyvConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/20 3:47 PM
 * <p>
 */
abstract class Parse {

    @IntDef({DyvConstant.PARSE_TYPE_VIEW, DyvConstant.PARSE_TYPE_DATA, DyvConstant.PARSE_TYPE_EVENT})
    @Retention(RetentionPolicy.RUNTIME)
    @interface ParseType {
    }

    Map<String, Object> sourceData;


    public Parse(Map<String, Object> sourceData) {
        this.sourceData = sourceData;
    }

    public Map<String, Object> obtainSourceData(){
        return sourceData;
    }

    public abstract Object parse();

    public static <T extends Parse> T createParse(Map<String, Object> sourceData, @ParseType int parseType) {

        Parse target = null;

        switch (parseType) {

            case DyvConstant.PARSE_TYPE_VIEW:

                target = new ViewParse(sourceData);
                break;

            case DyvConstant.PARSE_TYPE_DATA:

                target = new DataParse(sourceData);
                break;

            case DyvConstant.PARSE_TYPE_EVENT:

                target = new EventParse(sourceData);
                break;
        }

        return (T) target;
    }


}
