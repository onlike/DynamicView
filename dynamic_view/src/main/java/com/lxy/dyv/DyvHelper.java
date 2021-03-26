package com.lxy.dyv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.URLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/2/19 6:46 PM
 * <p>
 */
public final class DyvHelper {


    public static int getDeviceWidth() {
        return DyvConstant.sDEVICE_WIDTH;
    }

    public static int getDeviceHeight() {
        return DyvConstant.sDEVICE_HEIGHT;
    }

    public static int[] obtainScreenSize(Context ctx) {

        if (DyvConstant.sDEVICE_WIDTH != 0 && DyvConstant.sDEVICE_HEIGHT != 0)
            return new int[]{DyvConstant.sDEVICE_WIDTH, DyvConstant.sDEVICE_HEIGHT};

        Point point = new Point();

        WindowManager wm = (WindowManager) ctx.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        wm.getDefaultDisplay().getSize(point);

        DyvConstant.sDEVICE_WIDTH = point.x;

        DyvConstant.sDEVICE_HEIGHT = point.y;

        return new int[]{DyvConstant.sDEVICE_WIDTH, DyvConstant.sDEVICE_HEIGHT};
    }


    public static int parseInteger(Object value) {

        if (isNull(value)) return 0;


        if (value instanceof Integer) {

            return (Integer) value;
        } else if (value instanceof Number) {

            return ((Number) value).intValue();
        } else if (value instanceof String) {

            try {

                return (int) Double.parseDouble(String.valueOf(value));
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    public static double parseDouble(Object value) {

        if (isNull(value)) return 0;


        if (value instanceof Double) {

            return (Double) value;
        } else if (value instanceof Number) {

            return ((Number) value).doubleValue();
        } else if (value instanceof String) {

            try {

                return Double.parseDouble(String.valueOf(value));
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    public static int parseColor(String source) {

        if (TextUtils.isEmpty(source)) return 0;

        int color = 0;

        try {

            color = Color.parseColor(source);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return color;
    }

    public static int parseDrawableID(Context context, String source) {

        if (TextUtils.isEmpty(source)) return 0;

        int id = 0;

        try {

            id = context.getResources().getIdentifier(source, "drawable", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public static int percent2Real(double percent, int total) {

        double rp = percent / 100;

        return (int) (total * rp);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }


    public static boolean propertiesLegal(int properties) {
        return properties > 0;
    }

    public static boolean propertiesLegal(String properties) {
        return !TextUtils.isEmpty(properties);
    }

    public static boolean propertiesLegal(List<?> properties) {
        return properties != null && properties.size() > 0;
    }

    public static boolean isURLValue(String target) {
        return URLUtil.isNetworkUrl(target);
    }

    // use reflect
    @Deprecated
    public static boolean isDrawableValue(Context context, String target) {

        int id = context.getResources().getIdentifier(target, "drawable", context.getPackageName());

        return id > 0;
    }

    public static boolean isColorValue(String target) {

        boolean result = true;

        try {

            Color.parseColor(target);
        } catch (IllegalArgumentException e) {

            result = false;
        }

        return result;
    }

    public static Object get(Map<String, Object> sourceData, String key) {
        return sourceData.get(key);
    }

    public static String getString(Map<String, Object> sourceData, String key) {

        if (isNull(sourceData)) return "";

        Object result = sourceData.get(key);

        if (result instanceof String) {

            return result.toString();
        } else if (!DyvHelper.isNull(result)) {

            return String.valueOf(result);
        }
        return "";

    }

    public static int getInteger(Map<String, Object> sourceData, String key) {

        if (isNull(sourceData)) return 0;

        Object result = sourceData.get(key);

        if (result instanceof Integer) {

            return (Integer) result;
        } else if (result instanceof Number) {

            return ((Number) result).intValue();
        } else if (result instanceof String) {

            try {

                return (int) Double.parseDouble((String) result);
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    public static ArrayList<Object> getArray(Map<String, Object> sourceData, String key) {

        if (isNull(sourceData)) return new ArrayList<>();

        Object result = sourceData.get(key);

        if (result instanceof List) {

            return (ArrayList) result;
        }

        return new ArrayList<>();
    }

    public static Map<String, Object> getMap(Map<String, Object> sourceData, String key) {

        if (isNull(sourceData)) return new HashMap<>();

        Object result = sourceData.get(key);

        if (result instanceof Map) {

            return (Map) result;
        }

        return new HashMap<>();
    }

    public static boolean hasProperties(Map<String, Object> sourceData, String key) {
        return sourceData.containsKey(key);
    }

}
