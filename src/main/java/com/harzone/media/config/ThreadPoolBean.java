package com.harzone.media.config;

import com.harzone.media.pool.TaskThreadPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fengjr
 * @date 2021/2/20.
 */
@Configuration
public class ThreadPoolBean {
    @Value("${system.pool.core-size}")
    private Integer poolCoreSize;
    @Value("${system.pool.max-size}")
    private Integer poolMaxSize;

    @Bean("threadPool")
    public ThreadPoolExecutor threadPool(){
        return new TaskThreadPool(poolCoreSize, poolMaxSize).getPool();
    }

}
