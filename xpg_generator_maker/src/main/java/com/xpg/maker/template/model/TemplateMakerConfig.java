package com.xpg.maker.template.model;

import com.xpg.maker.meta.Meta;
import lombok.Data;

@Data
public class TemplateMakerConfig {
    private Long id;
    private Meta meta = new Meta();
    private String originProjectPath;
    TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();
    TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();
    TemplateMakerOutputConfig templateMakerOutputConfig = new TemplateMakerOutputConfig();
}
