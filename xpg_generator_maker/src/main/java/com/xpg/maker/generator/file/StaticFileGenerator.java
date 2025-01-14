package com.xpg.maker.generator.file;

import cn.hutool.core.io.FileUtil;

public class StaticFileGenerator {


    public static void copyFilesByHutool (String inputPath,String outputPath) {
        FileUtil.copy(inputPath,outputPath,true);// TODO
    }

//    public static void main (String[] args){
//
//        // get project root path
//        String projectPath = System.getProperty("user.dir");
//        File parentFile = new File(projectPath).getParentFile();
//
//        // assign inputPath
//        String inputPath = new File(parentFile,"xpg_generator_demo_projects/acm-template").getAbsolutePath();
//
//        //
//        copyFilesByHutool(inputPath, projectPath);
//
//        System.out.println(parentFile);
//    }


}
