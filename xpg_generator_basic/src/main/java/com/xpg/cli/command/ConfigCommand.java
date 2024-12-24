package com.xpg.cli.command;

import com.xpg.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "config",description = "查看参数", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{


    @Override
    public void run() {

        System.out.println("查看参数信息");

        Field[] fields = MainTemplateConfig.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型" + field.getType());
            System.out.println("--------------------");


        }
    }
}
