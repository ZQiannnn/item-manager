package com.hand.beans;

/**
 * DaoException : 封装Dao(数据库访问)层发生的异常
 *
 */
public class DaoException extends UserException {

    public DaoException(String msg) {
        super(msg);
    }
}
