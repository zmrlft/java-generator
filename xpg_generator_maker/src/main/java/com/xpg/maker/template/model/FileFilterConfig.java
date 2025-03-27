package com.xpg.maker.template.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileFilterConfig {

    private String range;
    private String rule;
    private String value;

}
