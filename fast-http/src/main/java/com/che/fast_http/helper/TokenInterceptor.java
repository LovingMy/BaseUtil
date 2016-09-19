package com.che.fast_http.helper;


import com.che.base_util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 全局自动刷新Token的抽象拦截器
 * <p>
 * 作者：余天然 on 16/9/5 下午3:31
 */
public abstract class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        //根据和服务端的约定,判断token过期
        if (isTokenExpired(response)) {
            LogUtil.print("静默自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
            String token = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = getNewRequest(chain, token);
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 使用新的Token，创建新的请求
     * <p>
     * 例如：
     * chain.request()
     * .newBuilder()
     * .header("Cookie", "JSESSIONID=" + token)
     * .build();
     */
    public abstract Request getNewRequest(Chain chain, String token);

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    public abstract boolean isTokenExpired(Response response);

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    public abstract String getNewToken();
}
