package com.che.baseutil.web;

import com.che.baseutil.BuildConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络接口实现类生产器
 * <p>
 * 作者：余天然 on 16/7/11 下午2:45
 */
public class WebClient {

    private final String DEFAULT_HOST_NAME = "http://baidu.com";//默认服务器地址;(不用管，反正会被替换掉)
    private final OkHttpClient client;

    /**
     * 创建网络辅助类
     */
    public WebClient() {
        //创建OkHttpClient.Builder
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        //添加自定义的配置,比如添加拦截器等
        okhttpBuilder = configOkHttp(okhttpBuilder);

        //创建OkHttpClient
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG == true ? Level.BODY : Level.NONE);
        okhttpBuilder.addInterceptor(logging);
        client = okhttpBuilder.build();
    }

    /**
     * 重写后，自由配置OkHttp
     *
     * @param builder
     * @return
     */
    public OkHttpClient.Builder configOkHttp(OkHttpClient.Builder builder) {
        return builder;
    }

    private Retrofit buildRxGson() {
        //创建Retrofit.Builder
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(DEFAULT_HOST_NAME)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()));
        //创建Retrofit
        return retrofitBuilder.build();
    }

    private Retrofit buildCallGson() {
        //创建Retrofit.Builder
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(DEFAULT_HOST_NAME)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()));
        //创建Retrofit
        return retrofitBuilder.build();
    }

    /**
     * 获取网络接口实现类
     * <p>
     * 默认:RX_GSON
     *
     * @param clazz 网络接口类
     * @return
     */
    public <T> T getWebInterface(Class<T> clazz) {
        return getWebInterface(clazz, RetrofitType.RX_GSON);
    }

    /**
     * 获取网络接口实现类
     *
     * @param clazz 网络接口类
     * @return
     */
    public <T> T getWebInterface(Class<T> clazz, RetrofitType type) {
        Retrofit retrofit = null;
        switch (type) {
            case RX_GSON:
                retrofit = buildRxGson();
                break;
            case CALL_GSON:
                retrofit = buildCallGson();
                break;
        }
        return retrofit.create(clazz);
    }

    /**
     * Retrofit类型
     */
    enum RetrofitType {
        CALL_GSON,
        RX_GSON,
    }
}
