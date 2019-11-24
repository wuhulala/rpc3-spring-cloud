package com.wuhulala.cache.impl;

import com.wuhulala.cache.WuhulalaCache;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public class WeakValueCacheTest {


    @Test
    public void get() throws InterruptedException {
        WuhulalaCache<String, Foo> cache = new WeakValueCache<>();
        String key = "key1";
        Foo value = new Foo("foo");

        cache.put(key, value);
        value = null;

        Assert.assertEquals("foo", cache.get(key).value().getName());
        System.out.println("-----------gc start -----------");
        System.gc();
        System.out.println("-----------gc end -----------");
        Thread.sleep(10000);
        // 测试gc后对象是否还存在
        Assert.assertNull(cache.get(key).value());
    }
}