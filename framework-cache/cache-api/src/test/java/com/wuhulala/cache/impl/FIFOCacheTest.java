package com.wuhulala.cache.impl;

import com.wuhulala.cache.WuhulalaCache;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class FIFOCacheTest {

    @Test
    public void get() {
        WuhulalaCache<String, String> cache = new FIFOCache<>(4);
        cache.put("1", "111");
        cache.put("2", "112");
        cache.put("3", "113");
        cache.put("4", "114");
        cache.put("5", "115");
        Assert.assertNull(cache.get("1"));
        Assert.assertEquals("112", cache.get("2").value());
        cache.remove("2");
        cache.put("6", "116");
        cache.put("7", "117");
        cache.printInfo();
        Assert.assertNull(cache.get("3"));
    }
}