package com.wuhulala.cache.redis;

import com.wuhulala.cache.ValueHolder;
import com.wuhulala.cache.WuhulalaCache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wuhulala<br>
 * @date 2019/12/3<br>
 * @since v1.0<br>
 */
public class RedisCache<K, V> implements WuhulalaCache<K, V> {

    private String host;
    private int port;
    private String username;
    private String password;

    private Jedis jedis;

    public RedisCache(String host, int port) {
        this.host = host;
        this.port = port;
        // TODO 分析一下 jedis poll
        JedisPool jedisPool = new JedisPool(this.host, this.port);
        this.jedis = jedisPool.getResource();
    }

    @Override
    public ValueHolder<V> get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {
        return false;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
