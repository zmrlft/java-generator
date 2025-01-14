package com.xpg.maker.model;

import lombok.Data;

/**
 * dynamic template config
 */
@Data
public class DataModel {
    /**
     * generate loop or not
     */
    private boolean loop;

    /**
     * author annotation
     */
    private String author = "everyone";

    /**
     * output information
     */
    private String outputText = "sum = ";

}
