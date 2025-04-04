package com.xpg.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TemplateMakerFileConfig {
    private List<FileInfoConfig> files;
    private FileGroupConfig fileGroupConfig;

    @Data
    public static class FileGroupConfig {
        private String condition;
        private String groupKey;
        private String groupName;
    }

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {
        private String path;
        private String condition;
        private List<FileFilterConfig> filterConfigList;//之前我这里写成了FilterConfigList导致在将json数据转化为这个类的时候一直转换不了，另外我发现hutool.JSONUtil.toBean转换的时候不支持json文件里有首字母大写
    }
}
