package com.hand.beans;

/**
 * UserException : 用户自定义异常
 *
 */
public class UserException extends RuntimeException {


    public UserException(String msg){
        super(msg);
    }

    /**
     * 异常发生时间
     */
    private long date = System.currentTimeMillis();

    public long getDate() {
        return date;
    }
}
