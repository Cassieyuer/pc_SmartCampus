package com.std.smartcampus.common.exception;


/**
 * 身份证号错误异常
 * @ClassName: IdCardException 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:56:04
 */
public class IdCardException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091268223287612630L;

	private Integer code;

    public IdCardException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
