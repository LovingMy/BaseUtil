package com.che.baseutil.model;

import com.che.baseutil.bean.LoginRequest;
import com.che.baseutil.bean.LoginResponse;
import com.che.baseutil.bean.RegistRequest;
import com.che.baseutil.bean.RegistResponse;

import rx.Observable;

/**
 * 登录/注册模块
 * <p>
 * 作者：余天然 on 16/9/19 上午10:58
 */
public interface ILoginModel {

    /**
     * 检查是否已经登录
     */
     Observable<Boolean> checkIsLogin();

    /**
     * 检查是否已经通过审核
     */
     Observable<Boolean> checkIsConfirmed();

    /**
     * 登录
     */
     Observable<LoginResponse> login(LoginRequest request);

    /**
     * 注册
     */
     Observable<RegistResponse> regist(RegistRequest request);

    /**
     * 退出登录
     */
     Observable<RegistResponse> exitLogin(RegistRequest request);

}
