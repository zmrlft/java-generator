package com.xpg.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.xpg.maker.generator.JarGenerator;
import com.xpg.maker.generator.ScriptGenerator;
import com.xpg.maker.generator.file.DynamicFileGenerator;
import com.xpg.maker.meta.Meta;
import com.xpg.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public  abstract class GeneraterTemplate {
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        // output root path
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:"+projectPath);
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        System.out.println("outputPath:"+outputPath);
        doGenerate(meta, outputPath);
    }

    /**
     * 生成
     * @param meta
     * @param outputPath
     * @throws TemplateException
     * @throws IOException
     * @throws InterruptedException
     */
    public void doGenerate(Meta meta, String outputPath) throws TemplateException,IOException,InterruptedException{
        if(!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 复制原始文件
        String sourceCopyDestPath = copySource(meta, outputPath);

        // 生成代码
        generateCode(meta, outputPath);

        // 构建jar包
        String jarPath = buildJar(outputPath,meta);

        // 构建脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        // 生成精简版程序
        buildDist(outputPath, jarPath, shellOutputFilePath, sourceCopyDestPath);
    }

    /**
     * 生成精简版程序
     * @param outputPath
     * @param jarPath
     * @param shellOutputFilePath
     * @param sourceCopyDestPath
     * @return 产物包路径
     */
    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        // generate simplified version program
        String distOutputPath = outputPath + "-dist";
        // copy jar
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath,targetAbsolutePath,true);
        // copy script
        FileUtil.copy(shellOutputFilePath,distOutputPath,true);
        FileUtil.copy(shellOutputFilePath + ".bat",distOutputPath,true);
        // copy template file
        FileUtil.copy(sourceCopyDestPath,distOutputPath,true);
//        return distOutputPath;
    }

    /**
     * 封装脚本
     * @param outputPath
     * @param jarPath
     * @return 脚本输出路径
     *
     */
    protected String buildScript(String outputPath, String jarPath) {
        // generate script
        String shellOutputFilePath = outputPath + File.separator + "generator";

        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     * 构建jar包
     * @param outputPath
     * @param meta
     * @return 返回 jar 包的相对路径
     * @throws IOException
     * @throws InterruptedException
     */
    protected String buildJar(String outputPath,Meta meta) throws IOException, InterruptedException {
        // build jar
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }


    protected void generateCode(Meta meta, String outputPath) throws IOException {
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

        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
        //cli.command.ConfigCommand
        inputFilePath = inputResourcePath  + "/templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
        //cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
        //cli.command.ListCommand
        inputFilePath = inputResourcePath  + "/templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
        //cli.command.CommandExecutor
        inputFilePath = inputResourcePath  + "/templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
        // Main
        inputFilePath = inputResourcePath  + "/templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        // generator DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        // generator StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        // generator MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        // generator pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        // generate readme.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        // copy original template file
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + "./source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        return sourceCopyDestPath;
    }
}
