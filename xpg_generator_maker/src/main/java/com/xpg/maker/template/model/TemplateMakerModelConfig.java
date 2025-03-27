package com.xpg.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TemplateMakerModelConfig {
    private List<ModelInfoConfig> models;
    private ModelGroupConfig modelGroupConfig;

    //todo 这里少了NoArgsConstruct注解
    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig{
        private String fieldName;
        private String type;
        private String description;
        private Object defaultValue;
        private String abbr;
        private String replaceText;
    }

    @Data
    public static class ModelGroupConfig{
        private String groupName;
        private String groupKey;
        private String condition;

    }
}
