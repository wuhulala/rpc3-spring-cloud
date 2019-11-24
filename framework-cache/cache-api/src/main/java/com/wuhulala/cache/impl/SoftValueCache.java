package com.wuhulala.cache.impl;

import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 软引用缓存，
 *
 * <p>内存空间足够就不回收，不够就回收</p>
 *
 * <p>强 => 软(SoftReference) => 弱(WekReference) => 虚(PhantomReference)</p>
 *
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public class SoftValueCache<K, V> implements WuhulalaCache<K, V> {

    private Map<K, ValueHolder<V>> map = new ConcurrentHashMap<>();

    @Override
    public ValueHolder<V> get(K key) {
        return map.get(key);
    }

    @Override
    public boolean put(K key, V value) {
        map.put(key, new SoftValueHolder<V>(value));
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

    static class SoftValueHolder<V> implements ValueHolder<V> {

        private SoftReference<V> v;

        public SoftValueHolder(V v) {
            this.v = new SoftReference<>(v);
        }

        public V value() {
            return this.v.get();
        }
    }
}
