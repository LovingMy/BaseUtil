package com.che.baseutil.web.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 响应值的基类
 * <p>
 * 作者：余天然 on 16/5/9 下午1:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponse implements Serializable {
    private int resultCode;//请求标识，服务器从终端请求中复制该字段到应答字段，若没有，置为null
    private int replyCode;//请求返回值：0成功；非0失败
    private long serverTime;//服务器时间
    private String message;//应答信息：对 replyCode\resultCode 的文字描述
}
