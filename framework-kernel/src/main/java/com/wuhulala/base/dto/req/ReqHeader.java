package com.wuhulala.base.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @since  v1.0<br>
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description 请求头<br>
 */
public class ReqHeader implements Serializable {

    /** 用户id */
    @Getter @Setter
    private String userId;

}
