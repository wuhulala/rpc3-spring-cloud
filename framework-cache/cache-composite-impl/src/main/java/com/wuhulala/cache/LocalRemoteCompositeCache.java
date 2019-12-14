package com.wuhulala.cache;

import com.wuhulala.cache.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/**
 * 本地&远程混合缓存
 *
 * <p>对于多实例的情况下，不仅仅会使用本地缓存，并且需要使用分布式缓存。</p>
 * <p>这里就是使用多级缓存，所以可以自由定制组合！</p>
 *
 * @author wuhulala<br>
 * @date 2019/12/14<br>
 * @since v1.0<br>
 */
public class LocalRemoteCompositeCache<K, V> implements WuhulalaCache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(LocalRemoteCompositeCache.class);

    private Boolean writeLocal;

    private WuhulalaCache<K, V> localCache;

    private Boolean writeRemote;

    private WuhulalaCache<K, V> remoteCache;

    private ThreadPoolExecutor threadPoolExecutor;

    public LocalRemoteCompositeCache() {
    }

    public LocalRemoteCompositeCache(WuhulalaCache<K, V> localCache, WuhulalaCache<K, V> remoteCache) {
        this.localCache = localCache;
        this.remoteCache = remoteCache;
    }

    @Override
    public void init() {

    }

    @Override
    public ValueHolder<V> get(K key) {
        ValueHolder<V> value = localCache.get(key);
        // 使用空字符串作为空值，所以需要注意空串不要作为有效值放到缓存里面
        if (value.value() == NULL_OBJECT) {
            return null;
        }
        if (value.value() != null) {
            return value;
        }
        Future<ValueHolder<V>> remoteValue = threadPoolExecutor.submit(() -> remoteCache.get(key));

        try {
            value = remoteValue.get(3000, TimeUnit.MILLISECONDS);
            localCache.put(key, value.value());
        } catch (Exception e) {
            throw new CacheException(e);
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            return;
        }

        final Object object = copy(value);
        if (writeLocal) {
            localCache.put(key, value);
        }
        if (writeRemote) {
            threadPoolExecutor.execute(() -> {
                try {
                    remoteCache.put(key, value);
                } catch (Exception e) {
                    logger.error("update remote cache error, key : {}", key, e);
                }
            });
        }

    }

    public Object copy(V value) {
        return value;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
