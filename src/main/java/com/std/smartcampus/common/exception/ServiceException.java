package com.std.smartcampus.common.exception;

/**
 * 系统异常
 * @author zslin.com 20160514
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -4555331337009026323L;

    public ServiceException() {
        super();
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }
}
