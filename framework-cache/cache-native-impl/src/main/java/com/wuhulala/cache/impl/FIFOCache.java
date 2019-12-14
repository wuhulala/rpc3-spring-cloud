package com.wuhulala.cache.impl;

import com.wuhulala.cache.DefaultValueHolder;
import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * FIFO(先进先出)
 *
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class FIFOCache<K, V> implements WuhulalaCache<K, V> {

    private Deque<K> keyList;

    private Map<K, ValueHolder<V>> store;

    private int limit;

    public FIFOCache(int limit) {
        this.limit = limit;
        store = new HashMap<>(limit);
        keyList = new LinkedList<>();
    }

    @Override
    public ValueHolder<V> get(K key) {
        return store.get(key);
    }

    @Override
    public void put(K key, V value) {
        removeIfFull(key);
        store.put(key, new DefaultValueHolder<>(value));
        return true;
    }


    @Override
    public ValueHolder<V> remove(K key) {
        keyList.remove(key);
        return store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }


    private void removeIfFull(K key) {
        keyList.addLast(key);
        if (limit < keyList.size()){
            K oldestKey = keyList.removeFirst();
            System.out.println(oldestKey);
            store.remove(oldestKey);
        }
    }

    @Override
    public void printInfo() {
        System.out.println(store);
    }
}
