package com.wuhulala.cache;

/**
 * 本地&远程混合缓存 创建工厂
 *
 * @author wuhulala<br>
 * @date 2019/12/14<br>
 * @since v1.0<br>
 */
public class LocalRemoteCompositeCacheFactory {

    public static <K, V> LocalRemoteCompositeCache newInstance(WuhulalaCache<K, V> localCache,
                                                               WuhulalaCache<K, V> remoteCache) {
        LocalRemoteCompositeCache<K, V> resultCache = new LocalRemoteCompositeCache<>(localCache, remoteCache);
        return resultCache;
    }

}
