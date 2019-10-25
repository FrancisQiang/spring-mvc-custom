package com.lgq.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class ModelAndView {

    private Map<String,Object> model = new HashMap<>();

    public Map<String, Object> getModel() {
        return model;
    }

    public void addAttribute(String attributeName, String attributeValue) {
        model.put(attributeName, attributeValue);
    }
}
