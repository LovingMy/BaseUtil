package com.che.baseutil.web.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录
 * <p>
 * 作者：余天然 on 16/5/5 下午5:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BaseResponse {
    private String session = "";
    private long uid = 0;//用户id
    private String trueName = "";// 用户真名
    private String userName = "";//用户昵称
    private String password = "";//用户密码
    private boolean hasCreatePrivilege = false;//是否有创建权限，默认无
}
