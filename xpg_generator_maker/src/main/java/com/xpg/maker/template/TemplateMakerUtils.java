package com.xpg.maker.template;

import cn.hutool.core.util.StrUtil;
import com.xpg.maker.meta.Meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TemplateMakerUtils {
    public static List<Meta.FileConfig.FileInfo> removeGroupFilesFromRoot (List<Meta.FileConfig.FileInfo> fileInfoList){

        //get group files in fileInfoList
        List<Meta.FileConfig.FileInfo> gourpFileInfoList = fileInfoList.stream()
                .filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupKey()))
                .collect(Collectors.toList());

        //扁平化
        List<Meta.FileConfig.FileInfo> gourpInnerFileInfoList = gourpFileInfoList.stream()
                .flatMap(fileInfo -> fileInfo.getFiles().stream())
                .collect(Collectors.toList());

        //get set of files inputPath
        Set<String> fileInputPathSet = gourpInnerFileInfoList.stream()
                .map(Meta.FileConfig.FileInfo::getInputPath)
                .collect(Collectors.toSet());

        //remove inputPath of set in fileInfoList
        return fileInfoList.stream()
                .filter(fileInfo -> !fileInputPathSet.contains(fileInfo.getInputPath()))
                .collect(Collectors.toList());
    }
}
