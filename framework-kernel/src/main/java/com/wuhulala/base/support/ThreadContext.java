package com.wuhulala.base.support;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description 线程 上下文<br>
 * @since v1.0<br>
 */
public class ThreadContext {

    /** 当前线程id */
    private final long currentThreadId;

    /** 当前开始执行时间 */
    private final long beginTime;

	private final Map<String, Object> attrMap = new HashMap<>();

    /** 线程上下文 */
    private static final ThreadLocal<ThreadContext> LOCAL = ThreadLocal.withInitial(ThreadContext::new);

	private ThreadContext() {
		currentThreadId = Thread.currentThread().getId();
		beginTime = System.currentTimeMillis();
	}

	/**
	 * @return 当前线程id
	 */
	public long getCurrentThreadId() {
		return this.currentThreadId;
	}

	/**
	 * @return 上下文对象实例化时间
	 */
	public long getBeginTime() {
		return this.beginTime;
	}

    /**
     * @return 上下文当前执行时间
     */
	public long intervalMs(){
	    return System.currentTimeMillis() - this.beginTime;
    }

	/**
	 * 设置当前线程名
	 */
	public ThreadContext setName(String name) {
		Thread.currentThread().setName(name);
		return this;
	}

	/**
	 * 获取当前线程名
	 * 
	 */
	public String getName() {
		return Thread.currentThread().getName();
	}

	/**
	 * 放入上下文数据
	 * 
	 * @param key 键
	 * @param value 值
	 */
	public ThreadContext put(String key, Object value) {
		Assert.notNull(key, "Context attribute key must not be null");
		this.attrMap.put(key, value);
		return this;
	}

	/**
	 * 从上下文获取数据
	 */
	public Object get(String key) {
		return this.attrMap.get(key);
	}


	/**
	 * 获取当前线程上下文.
	 * 
	 */
	public static ThreadContext get() {
		return LOCAL.get();
	}

	/**
	 * 清理当前线程上下文缓存.
	 */
	public static void remove() {
		LOCAL.remove();
	}

}