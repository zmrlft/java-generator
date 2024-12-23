package com.xpg.generator;

import com.xpg.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



/**
 * Dynamic file generate
 *
 */
public class DynamicGenerator {

    /**
     *
     * @param inputPath 模板文件路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath,String outputPath,Object model) throws IOException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        configuration.setDefaultEncoding("utf-8");

        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        Writer out = new FileWriter("MainTemplate.java");
        try {
            template.process(model,out);
        } catch (TemplateException e) {
            System.out.println("template exception!{}"+e);
            // log.error("template exception!",e);
        } catch (IOException e){
            System.out.println("when template.process IO exception!{}"+e);
            // log.error("when template.process IO exception!",e);
        }

        out.close();

    }

    public static void main(String[] args) throws IOException, TemplateException {

        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("xpg橡皮膏");
        // no loop
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果哈1：");

        doGenerate(inputPath,outputPath,mainTemplateConfig);

    }
}
