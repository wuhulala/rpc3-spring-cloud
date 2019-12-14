package com.wuhulala.cache.exception;

/**
 * @author wuhulala<br>
 * @date 2019/12/14<br>
 * @since v1.0<br>
 */
public class CacheException extends RuntimeException{

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

    protected CacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
