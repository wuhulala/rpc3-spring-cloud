package com.wuhulala.base.dto.resp;

import java.io.Serializable;

public class PageInfo implements Serializable {

    /**
     * 默认每页条数
     */
    public final static int DEFAULT_PAGE_SIZE = 10;

    private int start;

    private int limit;

    private int count;

    private int page = 1;

    private int totalPage;

    public PageInfo() {
    }

    public PageInfo(int page, int limit) {
        this.page = page > 0 ? page : 1;
        this.limit = limit > 0 ? limit : DEFAULT_PAGE_SIZE;
        this.start = (this.page - 1) * this.limit;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.totalPage = count / limit + count % limit > 0 ? 1 : 0;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Boolean hasNext() {
        return totalPage > page;
    }

    public Boolean hasPre() {
        return page > 1;
    }

}
