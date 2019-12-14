package com.wuhulala.cache.impl;

import com.wuhulala.cache.DefaultValueHolder;
import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU(Least Recently Used/最近时间访问) 实现(非线程安全)
 *
 * <p>通过LinkedHashMap的重写{@link LinkedHashMap#removeEldestEntry(Map.Entry)}方法。</p>
 * <p>LinkedHashMap 已经实现了LRU算法</p>
 *
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class LRUCache<K, V> implements WuhulalaCache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(LRUCache.class);

    private Map<K, ValueHolder<V>> internalCache;

    public LRUCache(int limit) {
        logger.info("initialize a size of {} LRUCache", limit);
        internalCache = new LinkedHashMap<K, ValueHolder<V>>(limit, .75F, true) {
            private static final long serialVersionUID = 4267176411845948333L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, ValueHolder<V>> eldest) {
                return size() > limit;
            }
        };
    }


    @Override
    public ValueHolder<V> get(K key) {
        return internalCache.get(key);
    }

    @Override
    public void put(K key, V value) {
        internalCache.put(key, new DefaultValueHolder<>(value));
        return true;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return internalCache.remove(key);
    }

    @Override
    public void clear() {
        internalCache.clear();
    }
}
