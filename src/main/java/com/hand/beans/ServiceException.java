package com.hand.beans;

/**
 * ServiceException : 封装业务层发生的异常
 *
 */
public class ServiceException extends UserException {

    public ServiceException(String msg) {
        super(msg);
    }
}
