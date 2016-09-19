package com.che.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk动态代理
 * <p>
 * 作者：余天然 on 16/9/18 下午3:47
 */
public class JdkProxyFactory implements InvocationHandler {
    private Object target;

    //创建代理类
    public Object createProxy(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //改变委托类方法的调用逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        if (method.getName().equals("login")) {
            doBefore();
            result = method.invoke(target, args);
        }
        if (method.getName().equals("updateInfo")) {
            doBefore();
            result = method.invoke(target, args);
            doAfter();
        }
        return result;
    }

    private void doBefore() {
        System.out.println("JDK动态代理:前置动作……");
    }

    private void doAfter() {
        System.out.println("JDK动态代理:后置动作……");
    }

}
