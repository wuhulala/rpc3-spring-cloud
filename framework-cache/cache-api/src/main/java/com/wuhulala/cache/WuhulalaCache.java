package com.wuhulala.cache;

/**
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public interface WuhulalaCache<K, V> {

    ValueHolder<V> get(K key);

    boolean put(K key, V value);

    ValueHolder<V> remove(K key);

    void clear();

    default void printInfo(){};

}
