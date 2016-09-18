package com.che.baseutil.proxy;

import org.junit.Before;
import org.junit.Test;

public class ProxyTestClient {

    private UserImpl target;//被代理类

    @Before
    public void setUp() {
        target = new UserImpl();
    }

    /**
     * 原始
     */
    @Test
    public void testTarget() {
        target.login();
        target.updateInfo();
    }

    /**
     * 静态代理
     */
    @Test
    public void testStaticProxy() {
        UserStaticProxy staticProxy = new UserStaticProxy(target);
        staticProxy.login();
        staticProxy.updateInfo();
    }

    /**
     * Jdk动态代理
     */
    @Test
    public void testJdkProxy() {
        JdkProxyFactory jdkFactory = new JdkProxyFactory();
        IUser jdkProxy = (IUser) jdkFactory.createProxy(target);
        jdkProxy.login();
        jdkProxy.updateInfo();
    }

    /**
     * Cglib动态代理
     * <p>
     * Jdk只能对接口进行代理，而cglib却可以对普通类进行代理！
     * <p>
     * 注意：此代码在Java环境可运行，但在Android环境会报错！
     * 原因：Java是JVM虚拟器,Android是DarvikVM虚拟机,类加载机制有区别，而Cglib是基于继承的字节码生成技术，所有有问题。
     * Android中推荐使用AspectJ，不过这个玩意在AS中的配置有点麻烦
     */
    @Test
    public void testCglibProxy() {
        CglibProxyFactory cglibFactory = new CglibProxyFactory();
        IUser cglibProxy = (IUser) cglibFactory.createProxy(target);
        cglibProxy.login();
        cglibProxy.updateInfo();
    }

}