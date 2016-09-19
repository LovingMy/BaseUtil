package com.che.test.web;

import com.che.test.web.bean.OrderListRequest;
import com.che.test.web.bean.TestLoginRequest;
import com.che.test.web.bean.OrderListResponse;
import com.che.test.web.bean.TestLoginResponse;
import com.che.test.web.bean.VersionResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 网络接口
 */
public interface WebInterface {

    String HOST_NAME = "http://jpjc.che.com";//服务器地址;

    @POST(HOST_NAME + "/jcsapp/user/login")
    Observable<TestLoginResponse> login(@Body TestLoginRequest request);//登录

    @POST(HOST_NAME + "/jcsapp/user/version")
    Observable<VersionResponse> version(@Body Object request); //更新apk

    @POST(HOST_NAME + "/jcsapp/check/list")
    Observable<OrderListResponse> list(@Body OrderListRequest request);//获取检测订单列表

}
