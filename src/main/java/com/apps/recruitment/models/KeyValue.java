package com.apps.recruitment.models;

public class KeyValue {
    private String key;
    private Long value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public KeyValue(String department, Long total) {
        this.key = department;
        this.value = total;
    }

    public KeyValue() {
    }
}
