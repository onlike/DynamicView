package com.lxy.dynamicview.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxy.dyv.data.Data;
import com.lxy.dyv.event.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/3/14 1:03 PM
 * <p>
 */
public class Utils {

    public static void showToast(Context context, Data data, Event event) {

        StringBuilder msg = getMsg(event, data);

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static StringBuilder getMsg(Event event, Data data) {

        StringBuilder msg = new StringBuilder();
        msg.append("viewTag = ");
        msg.append(event.viewTag());

        msg.append("\n");


        List<String> action = event.action();

        msg.append("viewEvent = ");

        for (int i = 0; i < action.size(); i++) {

            msg.append(event.action().get(i));

            if (i != action.size() - 1) {
                msg.append(" & ");
            }
        }

        if (data != null) {
            Map<String, String> origin = data.origin();

            if (origin != null) {

                msg.append("\n");
                msg.append("viewData = ");

                for (String s : origin.keySet()) {

                    msg.append("{");
                    msg.append(s);
                    msg.append(" : ");
                    msg.append(origin.get(s));
                    msg.append("}");
                }
            }
        }

        return msg;
    }


    public static String obtainDataForAssets(Activity activity, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = activity.getAssets();
        InputStreamReader inputStreamReader = null;
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(inputStreamReader = new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    public static HashMap<String, Object> toJSONMap(String target) {

        return new Gson().fromJson(target, HashMap.class);
    }

    public static <T> T toJSONObject(String target, Class<T> clz){
        return new Gson().fromJson(target, clz);
    }
}
