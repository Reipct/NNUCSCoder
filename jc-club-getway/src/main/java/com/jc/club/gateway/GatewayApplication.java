package com.jc.club.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动器
 *
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/19
 */
@SpringBootApplication
@ComponentScan("com.jc")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
