package com.jc.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jc.oss.adapter.StorageAdapter;
import com.jc.oss.adapter.AliStorageAdapter;
import com.jc.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/20
 */
@Configuration
@RefreshScope
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;


    @Bean
    @RefreshScope
    public StorageAdapter storageService() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType))
            return new AliStorageAdapter();
        else {
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
        }

    }
}
