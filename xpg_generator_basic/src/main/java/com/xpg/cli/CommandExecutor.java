package com.xpg.cli;

import com.xpg.cli.command.ConfigCommand;
import com.xpg.cli.command.GenerateCommand;
import com.xpg.cli.command.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "xpg",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }


    public void run() {

        System.out.println("请输入具体命令，或者输入--help查看帮助");

    }
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }

}
