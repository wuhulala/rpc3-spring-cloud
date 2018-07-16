package com.wuhulala.base.dto.req;

import java.io.Serializable;

/**
 * @since  v1.0<br>
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description 请求<br>
 */
public class BaseReq<T> implements Serializable {

    public BaseReq() {
    }

    public BaseReq(T query) {
        this.query = query;
    }

    /** 查询条件 */
    private T query;

    /** 查询请求头 */
    private ReqHeader header;


    public ReqHeader getHeader() {
        return header != null ? header : new ReqHeader();
    }

    public BaseReq<T> setHeader(ReqHeader header) {
        if (header == null) {
            header = new ReqHeader();
        }
        this.header = header;
        return this;
    }


    public T getQuery() {
        return query;
    }

    public BaseReq<T> setQuery(T query) {
        this.query = query;
        return this;
    }
}