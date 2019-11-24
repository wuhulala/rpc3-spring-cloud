package com.wuhulala.cache;

/**
 * @author wuhulala<br>
 * @date 2019/11/24<br>
 * @since v1.0<br>
 */
public class DefaultValueHolder<V> implements ValueHolder<V> {

    private V target;

    public DefaultValueHolder(V target) {
        this.target = target;
    }

    @Override
    public V value() {
        return target;
    }
}
