package com.xpg.generator;

import com.xpg.model.MainTemplateConfig;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

public class MainGenerator {


    public static void doGenerate (Object model) throws IOException {

        String projectPath = System.getProperty("user.dir");
        System.out.println("user.dir"+projectPath);
        File parentFile = new File(projectPath).getParentFile();

        String inputPath = new File(parentFile,"xpg_generator_demo_projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        System.out.println(inputPath+"\n"+outputPath);

        // generate static file
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

        // generate dynamic file
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";



        DynamicGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);

    }

    public static void main (String[] args) throws IOException {

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("xpg橡皮膏");
        // no loop
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果哈2：");
        doGenerate(mainTemplateConfig);



    }
}
