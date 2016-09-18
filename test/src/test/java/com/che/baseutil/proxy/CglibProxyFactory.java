package com.che.baseutil.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理
 * <p>
 * 作者：余天然 on 16/9/18 下午3:48
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    //创建代理类
    public Object createProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //改变委托类方法的调用逻辑
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        if (method.getName().equals("login")) {
            doBefore();
            result = proxy.invokeSuper(target, args);
        }
        if (method.getName().equals("updateInfo")) {
            doBefore();
            result = proxy.invokeSuper(target, args);
            doAfter();
        }
        return result;
    }

    private void doBefore() {
        System.out.println("Cglib动态代理:前置动作……");
    }

    private void doAfter() {
        System.out.println("Cglib动态代理:后置动作……");
    }
}