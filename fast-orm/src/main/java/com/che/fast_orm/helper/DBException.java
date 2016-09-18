package com.che.fast_orm.helper;

/**
 * 作者：余天然 on 16/9/18 下午5:16
 */
public class DBException extends Exception {
    private static final long serialVersionUID = 1L;

    public DBException() {
    }

    public DBException(String detailMessage) {
        super(detailMessage);
    }

    public DBException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DBException(Throwable throwable) {
        super(throwable);
    }
}
