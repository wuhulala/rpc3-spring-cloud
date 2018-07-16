package com.wuhulala.base.dto.resp;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wuhulala.base.constants.BaseConstants;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @since  v1.0<br>
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description 响应<br>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResp<T> implements Serializable {
    
    /** 状态码*/
    @Getter
    private String code;
    
    /** 状态信息*/
    @Getter
    private String msg;

    /** 异常信息*/
    @Getter
    private String cause;

    /** 分页信息 */
    @Getter
    private PageInfo pageInfo;

    /** 单行数据查询 */
    @Getter
    private T item;

    /** 列表查询 */
    @Getter
    private List<T> list;

    /**灵活的数据查询 */
    @Getter
    private Map<String, Object> map;

    public BaseResp() {
        this.code = BaseConstants.RESULT_CODE_SUCCESS;
        this.msg = BaseConstants.RESULT_MESSAGE_SUCCESS;
    }
    

    public BaseResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public BaseResp<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public BaseResp<T> setResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseResp<T> setResultInfo(String code, String msg, String cause) {
        this.code = code;
        this.msg = msg;
        this.cause = cause;
        return this;
    }
    

    public BaseResp<T> setResultMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public boolean isError() {
        return !this.code.equals(BaseConstants.RESULT_CODE_SUCCESS);
    }

    public boolean isSuccess() {
        return this.code.equals(BaseConstants.RESULT_CODE_SUCCESS);
    }

    public BaseResp<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public BaseResp<T> setItem(T item) {
        this.item = item;
        return this;
    }

    public BaseResp<T> setList(List<T> list) {
        this.list = list;
        return this;
    }


    public BaseResp<T> setMap(Map<String, Object> data) {
        this.map = data;
        return this;
    }
}
