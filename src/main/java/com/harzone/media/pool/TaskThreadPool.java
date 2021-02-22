package com.harzone.media.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fengjr
 * @date 2021/2/19.
 */
public class TaskThreadPool {

    private ThreadPoolExecutor pool;

    public TaskThreadPool(Integer poolCoreSize, Integer poolMaxSize) {
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        builder.setNameFormat("ffmpeg线程池-%d");
        pool = new ThreadPoolExecutor(poolCoreSize, poolMaxSize, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(200),
                builder.build(), new ThreadPoolExecutor.AbortPolicy());
        pool.allowCoreThreadTimeOut(false);
    }

    public ThreadPoolExecutor getPool() {
        return pool;
    }
}
