package com.xpg.maker.template.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

@Getter
public enum FileFilterRangeEnum {

    FILE_NAME("文件名称", "fileName"),
    FILE_CONTENT("文件内容", "fileContent");

    private final String text;
    private final String value;

    FileFilterRangeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    //get enum with value
    public static FileFilterRangeEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;

        } else {
            for (FileFilterRangeEnum anEnum : FileFilterRangeEnum.values()) {
                if (anEnum.value.equals(value)) {
                    return anEnum;
                }
            }
        }
        return null;
    }

}







