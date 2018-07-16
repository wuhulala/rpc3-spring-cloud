/**
 *
 */
package com.wuhulala.base.exception;

import com.wuhulala.base.support.ErrorInfoSupport;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;


/**
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description 系统内基础异常，所有运行时异常需要继承此异常 <br>
 * @since v1.0<br>
 */
public class BaseException extends RuntimeException {

    /** 是否是自定义错误号 */
    public static final boolean IS_CUSTOM = true;

    /** 是否是系统错误号 */
    public static final boolean IS_SYSTEM = false;

    /**自定义错误号，如果需要自定义错误号，统一使用此错误号*/
    public static final String CUSTOM_ERROR_CODE = "-666666";
    public static final String CUSTOM_ERROR_MSG = "系统异常";

    @Getter
    private String errorCode;

    @Getter
    private String errorInfo;

    /**
     * 构造函数
     *
     * @param exNo        错误号
     * @param errorParams 错误信息或错误信息参数
     */
    public BaseException(String exNo, Object... errorParams) {
        this(IS_SYSTEM, exNo, errorParams);
    }

    /**
     * 构造函数
     *
     * @param isCustom    是否自定义错误号和错误信息
     * @param exNo        错误号
     * @param errorParams 错误信息或错误信息参数
     */
    public BaseException(boolean isCustom, String exNo, Object... errorParams) {
        this.errorCode = exNo;

        if (IS_CUSTOM == isCustom) {
            if (errorParams != null && errorParams.length == 1) {
                this.errorInfo = errorParams[0].toString();
            } else if (errorParams != null && errorParams.length > 1) {
                this.errorInfo = MessageFormat.format(errorParams[0].toString(), ArrayUtils.remove(errorParams, 0));
            } else {
                this.errorInfo = CUSTOM_ERROR_MSG;
            }
        } else {
            this.errorInfo = ErrorInfoSupport.getErrorInfo(exNo, errorParams);
        }
    }

    /**
     * 构造函数
     *
     * @param e           异常
     * @param exNo        错误号
     * @param errorParams 错误信息，错误信息参数
     */
    public BaseException(Throwable e, String exNo, Object... errorParams) {
        super(e);
        if (StringUtils.isNotBlank(exNo)) {
            this.errorCode = exNo;
            this.errorInfo = ErrorInfoSupport.getErrorInfo(exNo, errorParams);
        }
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = this.errorInfo;
        }
        return message;
    }
}
