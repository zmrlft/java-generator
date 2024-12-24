package com.xpg.cli.command;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command(name = "list",description = "列出文件", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{


    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        String parentPath = new File(projectPath).getParent();
        String inputPath = new File(parentPath, "xpg_generator_demo_projects/acm-template").getAbsolutePath();
        List<File>  list = FileUtil.loopFiles(inputPath);
        for (File file : list){
            System.out.println(file);
        }

    }
}
