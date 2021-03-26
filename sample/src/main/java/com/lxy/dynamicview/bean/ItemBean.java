package com.lxy.dynamicview.bean;

import java.util.HashMap;

/**
 * author: lxy
 * created on: 2021/3/14 2:12 PM
 * <p>
 */
public class ItemBean {

    private HashMap<String, Object> template;

    private HashMap<String, Object> properties;

    public HashMap<String, Object> getTemplate() {
        return template;
    }

    public void setTemplate(HashMap<String, Object> template) {
        this.template = template;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }
}
