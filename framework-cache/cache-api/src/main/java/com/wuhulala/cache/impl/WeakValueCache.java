package com.wuhulala.cache.impl;

import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public class WeakValueCache<K, V> implements WuhulalaCache<K, V> {

    private Map<K, ValueHolder<V>> map = new ConcurrentHashMap<>();

    @Override
    public ValueHolder<V> get(K key) {
        return map.get(key);
    }

    @Override
    public boolean put(K key, V value) {
        map.put(key, new WeakValueHolder<V>(value));
        return true;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
