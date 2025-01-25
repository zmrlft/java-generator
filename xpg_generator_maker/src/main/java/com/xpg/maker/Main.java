package com.xpg.maker;

//import com.xpg.maker.cli.CommandExecutor;

import com.xpg.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {

        //args = new String[]{"generate","-l","-a","-o"};
        //args = new String[]{"config"};
        //args = new String[]{"list"};

        //CommandExecutor commandExecutor  = new CommandExecutor();
        //commandExecutor.doExecute(args);

        MainGenerator generator = new MainGenerator();
        generator.doGenerate();

        }

}
