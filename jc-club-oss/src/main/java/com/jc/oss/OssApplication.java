package com.jc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * oss服务启动
 *
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/19
 */
@SpringBootApplication
@ComponentScan("com.jc")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
