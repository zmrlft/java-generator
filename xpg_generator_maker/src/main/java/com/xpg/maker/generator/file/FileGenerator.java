package com.xpg.maker.generator.file;

import com.xpg.maker.model.DataModel;

import java.io.File;
import java.io.IOException;

public class FileGenerator {


    public static void doGenerate (Object model) throws IOException {

        String projectPath = System.getProperty("user.dir");
        System.out.println("user.dir"+projectPath);
        File parentFile = new File(projectPath).getParentFile();

        String inputPath = new File(parentFile,"xpg_generator_demo_projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        System.out.println(inputPath+"\n"+outputPath);

        // generate static file
        StaticFileGenerator.copyFilesByHutool(inputPath,outputPath);

        // generate dynamic file
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";



        DynamicFileGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);

    }

    public static void main (String[] args) throws IOException {

        DataModel dataModel = new DataModel();
        dataModel.setAuthor("xpg橡皮膏");
        // no loop
        dataModel.setLoop(false);
        dataModel.setOutputText("求和结果哈2：");
        doGenerate(dataModel);



    }
}
