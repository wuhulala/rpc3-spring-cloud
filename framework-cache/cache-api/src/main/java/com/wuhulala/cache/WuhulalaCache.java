package com.wuhulala.cache;

/**
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public interface WuhulalaCache<K, V> {

    public static final String NULL_OBJECT = new String();

    /**
     * 缓存初始化过程
     */
    default void init(){};

    ValueHolder<V> get(K key);

    void put(K key, V value);

    ValueHolder<V> remove(K key);

    void clear();

    default void printInfo(){};

}
