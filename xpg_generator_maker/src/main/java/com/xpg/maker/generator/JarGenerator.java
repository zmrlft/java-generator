package com.xpg.maker.generator;

import java.io.*;

public class JarGenerator {
    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        System.out.println("Generate jar file");
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        // to solit
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Process process = processBuilder.start();

        // read command input
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // wait Command execute finish
        int exitCode = process.waitFor();
        System.out.println("Exit code: " + exitCode);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("E:\\mycode\\xpg_generator\\xpg_generator_maker\\generated\\acm-template-pro-generator");
    }
}
