package com.jc.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信服务启动
 *
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/30
 */
@SpringBootApplication
@ComponentScan("com.jc")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class);
    }
}
