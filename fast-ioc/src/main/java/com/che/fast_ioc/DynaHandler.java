package com.che.fast_ioc;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理，调用activity里面的method：例如：onClick等
 * <p>
 * 作者：余天然 on 16/9/16 上午2:13
 */
public class DynaHandler implements InvocationHandler {

    private Activity activity;
    private Method method;

    public DynaHandler(Activity activity, Method method) {
        this.activity = activity;
        this.method = method;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        // 这里调用动态注入的方法
        return this.method.invoke(activity, args);
    }
}
