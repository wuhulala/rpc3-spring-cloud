package com.wuhulala.cache.impl;

import com.wuhulala.cache.DefaultValueHolder;
import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU （最不常用算法、Least Frequently Used） Un-ThreadSafe
 *
 * <p>找出访问频次最少的对象，替换掉</p>
 * <p>通过记录key的访问次数，和每个访问次数最小的key集合</p>
 *
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class LFUCache<K, V> implements WuhulalaCache<K, V> {

    private Map<K, ValueHolder<V>> store;

    private Map<K, Integer> keyVisited;

    private Map<Integer, LinkedHashSet<K>> countKeySets;

    private Integer minNum;

    private int limit;

    public LFUCache(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("cache size should not less than 0 .");
        }
        this.limit = size;
        store = new HashMap<>(size);
        keyVisited = new HashMap<>(size);
        countKeySets = new HashMap<>();
        this.minNum = Integer.MAX_VALUE;
    }

    @Override
    public ValueHolder<V> get(K key) {
        ValueHolder<V> v = store.get(key);
        if (v != null) {
            addKeyVisited(key);
        }
        return v;
    }

    private void addKeyVisited(K key) {
        int visitNum = keyVisited.getOrDefault(key, 0) + 1;
        keyVisited.put(key, visitNum);
        countKeySets.put(visitNum, countKeySets.getOrDefault(visitNum, new LinkedHashSet<>()));
        countKeySets.get(visitNum).add(key);
        if (visitNum > 1) {
            countKeySets.getOrDefault(visitNum - 1, new LinkedHashSet<>()).remove(key);
        }
        if (this.minNum > visitNum) {
            this.minNum = visitNum;
        }
    }

    @Override
    public void put(K key, V value) {
        removeLeastIfFull();
        addKeyVisited(key);
        System.out.println("--------------addKeyVisited---------------------");
        System.out.println(key);
        System.out.println(keyVisited);
        System.out.println(countKeySets);
        System.out.println("--------------addKeyVisited---------------------");
        store.put(key, new DefaultValueHolder<>(value));
        return true;
    }

    private void removeLeastIfFull() {
        // if not full, return
        if (this.keyVisited.keySet().size() < this.limit) {
            return;
        }
        if (this.minNum == Integer.MAX_VALUE) {
            return;
        }
        K minedKey = countKeySets.get(this.minNum).iterator().next();
        keyVisited.remove(minedKey);
        countKeySets.get(this.minNum).remove(minedKey);
        System.out.println("-----------------------------------");
        System.out.println(minedKey);
        System.out.println(keyVisited);
        System.out.println(countKeySets);
        System.out.println("-----------------------------------");
        store.remove(minedKey);
        // 如果当前访问频次里面没有key了,需要更新 minNum
        this.minNum = 1;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
        keyVisited.clear();
        countKeySets.clear();
        minNum = Integer.MAX_VALUE;
    }

    @Override
    public void printInfo() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "LFUCache{" +
                "store=" + store +
                ", keyVisited=" + keyVisited +
                ", countKeySets=" + countKeySets +
                ", minNum=" + minNum +
                ", limit=" + limit +
                '}';
    }
}
