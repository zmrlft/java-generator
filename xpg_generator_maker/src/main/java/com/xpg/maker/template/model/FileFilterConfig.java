package com.xpg.maker.template.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Builder
@Data //这里之前被我写成了@Gatter,导致hutool.JSONUtil.toBean老报错Caused by: cn.hutool.core.exceptions.UtilException: UtilException: ConvertException: Can not Converter from [cn.hutool.json.JSONObject] to [com.xpg.maker.template.model.FileFilterConfig]
public class FileFilterConfig {

    private String range;
    private String rule;
    private String value;

}
