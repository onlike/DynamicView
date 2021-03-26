package com.lxy.dyv;

import com.lxy.dyv.properties.GeneralProperties;
import com.lxy.dyv.view.FrameContainer;
import com.lxy.dyv.view.Image;
import com.lxy.dyv.view.LinearContainer;
import com.lxy.dyv.view.Text;
import com.lxy.dyv.view.VirtualView;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * author: lxy
 * created on: 2021/3/4 6:55 PM
 * <p>
 */
public final class VirtualViewCreator {

    public static VirtualView createVirtualView(int type){

        return _createVirtualView(type);
    }

    public static void injectProperties(VirtualView virtualView, Map<String, Object> data){

        Field[] fields = virtualView.getClass().getFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(GeneralProperties.class)) {

                GeneralProperties properties = field.getAnnotation(GeneralProperties.class);

                if (properties != null) {

                    Object value = data.get(properties.key());

                    try {

                        if (value instanceof Number) {

                            if (field.getType() == int.class){

                                field.set(virtualView, DyvHelper.parseInteger(value));
                            }else if (field.getType() == double.class) {

                                field.set(virtualView, DyvHelper.parseDouble(value));
                            }

                        } else {

                            field.set(virtualView, value);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static VirtualView _createVirtualView(int type) {
        VirtualView virtualView = null;
        switch (type) {

            case DyvConstant.VIEW_TYPE_FRAME:
                virtualView = new FrameContainer();
                break;

            case DyvConstant.VIEW_TYPE_LINEAR:
                virtualView = new LinearContainer();
                break;

            case DyvConstant.VIEW_TYPE_IMAGE:
                virtualView = new Image();
                break;

            case DyvConstant.VIEW_TYPE_TEXT:
                virtualView = new Text();
                break;

        }

        return virtualView;
    }

}
