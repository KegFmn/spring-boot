package com.likc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: likc
 * @Date: 2022/01/04/23:28
 * @Description: 多线程配置类
 */

@Slf4j
@EnableAsync
@Configuration
public class ExecutorConfig {

    private static final int COREPOOLSIZE = 5;
    private static final int MAXPOOLSIZE = 10;
    private static final int QUEUECAPACITY = 20;
    private static final int KEEPALIVESECONDS = 60;
    private static final String NAMEPREFIX = "async-service-";

    @Bean("Executor")
    public Executor asyncServiceExecutor() {
        log.info("------线程池开启------");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(COREPOOLSIZE);
        // 设置最大线程数
        executor.setMaxPoolSize(MAXPOOLSIZE);
        // 设置队列容量
        executor.setQueueCapacity(QUEUECAPACITY);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(KEEPALIVESECONDS);
        // 设置默认线程名称
        executor.setThreadNamePrefix(NAMEPREFIX);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
