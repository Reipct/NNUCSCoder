package com.jc.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jc.oss.entity.Result;
import com.jc.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/19
 */
@RestController
public class FileController {
    @Resource
    private FileService fileService;

    @NacosValue(value="${storage.service.type}",autoRefreshed = true)
    private String storageType;


    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBucket() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() throws Exception {
        return storageType;
    }


    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        return Result.ok(url);
    }
}
