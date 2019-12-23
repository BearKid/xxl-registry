package com.xxl.registry.client.util.json;

class JsonElement {
    private String key;
    private Object value;

    public JsonElement(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
