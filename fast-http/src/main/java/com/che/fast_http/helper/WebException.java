package com.che.fast_http.helper;

/**
 * 网络异常
 * <p>
 * 作者：余天然 on 16/9/16 下午6:18
 */
public class WebException extends Throwable {

    int errorCode;

    public WebException(String detailMessage) {
        super(detailMessage);
    }

    public WebException(int errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }
}
