package com.wuhulala.base.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础信息
 *
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @description o_o<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @since v1.0<br>
 */
public class BaseInfo implements Serializable{

    /**录入日期*/
    @Getter @Setter
    private Integer inputDate;

    /**录入时间*/
    @Getter @Setter
    private Integer inputTime;

    /**更新日期*/
    @Getter @Setter
    private Integer updateDate;

    /**更新时间*/
    @Getter @Setter
    private Integer updateTime;

    /**操作员Id*/
    @Getter @Setter
    private String operId;
}
