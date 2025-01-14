package com.xpg.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    private static volatile Meta mata;
    private MetaManager() {
    }
    public static Meta getMetaObject() {
        if (mata == null) {
            synchronized (MetaManager.class) {
                if (mata == null) {
                    mata = initMeta();
                }
            }
        }
        return mata;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);

        Meta.FileConfig fileConfig = newMeta.getFileConfig();
        // TODO 校验和处理默认值
        return newMeta;
    }

}
