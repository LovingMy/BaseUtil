package com.che.baseutil.web.helper;


import com.che.base_util.NetStateUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 */
public class CacheIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //无网的时候强制使用缓存
        if (NetStateUtil.getNetState() == NetStateUtil.NetState.NET_NO) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
        if (NetStateUtil.getNetState() != NetStateUtil.NetState.NET_NO) {
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
