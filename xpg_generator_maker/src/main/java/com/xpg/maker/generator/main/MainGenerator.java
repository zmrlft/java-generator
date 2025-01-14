package com.xpg.maker.generator.main;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.xpg.maker.generator.JarGenerator;
import com.xpg.maker.generator.ScriptGenerator;
import com.xpg.maker.generator.file.DynamicFileGenerator;
import com.xpg.maker.meta.Meta;
import com.xpg.maker.meta.MetaManager;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        // output root path
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:"+projectPath);
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        System.out.println("outputPath:"+outputPath);
        // read resources Dir
        ClassPathResource classPathResource = new ClassPathResource("");//类路径是 Java 应用程序在运行时查找类和资源的路径集合。这包括编译后的.class文件所在的目录以及添加到类路径中的其他资源目录或 JAR 文件。例如，在一个 Maven 项目中，src/main/resources目录下的资源会被放在类路径下。
        String inputResourcePath = classPathResource.getAbsolutePath();
        System.out.println("inputResourcePath:"+inputResourcePath);

        // Java base package path
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage,"."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;
        System.out.println("outputBaseJavaPackagePath:"+outputBaseJavaPackagePath);

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath  + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        System.out.println("inputFilePath:"+inputFilePath);
        System.out.println("outputFilePath:"+outputFilePath);

        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        //cli.command.ConfigCommand
        inputFilePath = inputResourcePath  + "/templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        //cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        //cli.command.ListCommand
        inputFilePath = inputResourcePath  + "/templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        //cli.command.CommandExecutor
        inputFilePath = inputResourcePath  + "/templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // Main
        inputFilePath = inputResourcePath  + "/templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // generator DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // generator StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // generator MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // generator pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        // build jar
        JarGenerator.doGenerate(outputPath);

        // generate script
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath,jarPath);




    }
}
