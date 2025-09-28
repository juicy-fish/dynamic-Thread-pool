package com.tc.middleware.dynamic.thread.pool.sdk.registry;

import com.tc.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @ClassName IRegistry
 * @Author 大马鲛鱼丸Gaga
 * @Description 注册中心接口
 * @Date 2025/9/28 21:10
 */
public interface IRegistry {

    // 上报线程池
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities);

    // 上报线程池的配置参数
    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
