package com.jc.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Description 线程池的config管理
 * @Author 12919
 * @Date 2025/1/6
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool() {
        return new ThreadPoolExecutor(20, 100, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(40), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
