package com.xpg.maker.meta.enums;

public enum ModelTypeEnum {
    BOOLEAN("布尔值","boolean"),
    STRING("字符串","String");

    private final String text;
    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
