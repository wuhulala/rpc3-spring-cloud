package com.wuhulala.cache.impl;

import com.wuhulala.cache.ValueHolder;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * <p>只要发现了gc，就会回收其内存空间</p>
 *
 * @author wuhulala<br>
 * @date 2019/11/21<br>
 * @since v1.0<br>
 */
public class WeakValueHolder<V> implements ValueHolder<V> {

    private WeakReference<V> v;

    public WeakValueHolder(V v) {
        this.v = new WeakReference<>(v);
    }

    public V value() {
        return this.v.get();
    }
}
