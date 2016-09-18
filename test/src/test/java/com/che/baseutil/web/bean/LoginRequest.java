package com.che.baseutil.web.bean;

import com.che.base_util.MD5Util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录
 * <p>
 * 作者：余天然 on 16/5/5 下午5:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequest {

    private String userName;//用户名
    private String password;// 密码
    private Boolean rememberMe;// 记住我

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = MD5Util.encode(password);
        this.rememberMe = true;
    }

}
