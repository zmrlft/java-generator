package com.xpg.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xpg.maker.meta.enums.FileGenerateTypeEnum;
import com.xpg.maker.meta.enums.FileTypeEnum;
import com.xpg.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * 元信息校验
 */
public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    public static void validAndFillModelConfig(Meta meta) {
        // modelConfig validate and fill default value
        Meta.ModelConfig modelConfig = meta.getModelConfig();

        // use Defensive statement 使用卫语句
        if (modelConfig == null) {
            return;
        }
        // modelConfig 默认值
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollectionUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList ){
            String modelInfoType = StrUtil.blankToDefault(modelInfo.getType(), ModelTypeEnum.STRING.getValue());
            modelInfo.setType(modelInfoType);
        }



    }

    public static void validAndFillFileConfig(Meta meta) {
        // fileConfig validate and fill default value
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }

        // sourceRootPath : required fields
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)){
            throw new MetaException("未填写sourceRootPath");
        }

        // inputRootPath : .source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
        // outputRootPath：默认为当前路径下的 generated
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }
        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }


        // fileInfo default value fill
        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollectionUtil.isNotEmpty(fileInfoList)) {
            return;
        }

        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList){
            String type = fileInfo.getType();
            // type is group , no valid
            // TODO

            // inputPath : required fields
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)){
                throw new MetaException("未填写 inputPath");
            }

            // outputPath : default equal to inputPath
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)){
                fileInfo.setOutputPath(inputPath);
            }

            //type : default inputPath has file suffix example .java type is file,if not, type is dir
            //String type = fileInfo.getType();
            if (StrUtil.isBlank(type)){
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                }else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }

            // generateType : if suffix of inputPath file is not ftl,generateType default value is static ,if not,type is dynamic
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)){
                if (inputPath.endsWith(".ftl")){
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                }else{
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }

                }
        }


    }


    public static void validAndFillMetaRoot(Meta meta) {
        // base information validate and fill default value
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "我的模板代码生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "yupi");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.yupi");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);

    }
}
