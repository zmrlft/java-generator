package com.xpg.maker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class ScriptGenerator {
    public static void doGenerate (String outputPath,String jarPath){

        // write script file directly
        // generate script file for linux
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash").append("\n");
        sb.append(String.format("java -jar %s \"$@\"",jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8),outputPath);
        // add permission
        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {

        }

        // generate win script
        sb = new StringBuilder();
        sb.append("@echo off").append("\n");
        sb.append(String.format("java -jar %s %%*",jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8),outputPath+".bat");
        }

    }


