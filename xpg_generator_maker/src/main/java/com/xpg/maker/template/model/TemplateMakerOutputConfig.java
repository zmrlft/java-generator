package com.xpg.maker.template.model;

import lombok.Data;


@Data
public class TemplateMakerOutputConfig {
    //从未分组文件中移除分组的文件
    private boolean removeGroupFilesFromRoot = true;

}
