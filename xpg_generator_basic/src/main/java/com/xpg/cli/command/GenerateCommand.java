package com.xpg.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.xpg.generator.MainGenerator;
import com.xpg.model.MainTemplateConfig;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate",description = "生成代码", mixinStandardHelpOptions = true)
public class GenerateCommand implements Callable<Integer> {

    @CommandLine.Option(names= {"-l","--loop"},arity = "0..1",description = "是否循环",interactive = true,echo = true)
    private boolean loop;

    @CommandLine.Option(names= {"-a","--author"},arity = "0..1",description = "作者",interactive = true,echo = true)
    private String author = "xpg";

    @CommandLine.Option(names= {"-o","--outputText"},arity = "0..1",description = "输出文本",interactive = true,echo = true)
    private String outputText = "sum = ";

    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setLoop(loop);
        mainTemplateConfig.setAuthor(author);
        mainTemplateConfig.setOutputText(outputText);
        //BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息："+mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;

    }



}
