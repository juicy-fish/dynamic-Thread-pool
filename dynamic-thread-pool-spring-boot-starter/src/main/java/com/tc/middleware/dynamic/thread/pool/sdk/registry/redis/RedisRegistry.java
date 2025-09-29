package com.tc.middleware.dynamic.thread.pool.sdk.registry.redis;

import com.tc.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.tc.middleware.dynamic.thread.pool.sdk.domain.model.valobj.RegistryEnumVO;
import com.tc.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * @ClassName RedisRegistry
 * @Author 大马鲛鱼丸Gaga
 * @Description TODO
 * @Date 2025/9/28 21:11
 */
public class RedisRegistry implements IRegistry {
    private final RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolEntities) {
        RList<ThreadPoolConfigEntity> list = redissonClient.getList(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEY.getKey());
        /**
         * 每次上报的都是完整的线程池配置列表
         * 不是增量更新，而是用新数据完全替换旧数据
         * 确保 Redis 中的列表与 threadPoolEntities 参数完全一致
         */
        list.delete();
        list.addAll(threadPoolEntities);
    }

    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey() + "_" + threadPoolConfigEntity.getAppName() + "_" + threadPoolConfigEntity.getThreadPoolName();
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(threadPoolConfigEntity, Duration.ofDays(30));
    }
}
