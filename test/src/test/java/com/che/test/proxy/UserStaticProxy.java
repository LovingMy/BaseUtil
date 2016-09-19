package com.che.test.proxy;

/**
 * 静态代理类
 * <p>
 * 作者：余天然 on 16/9/18 下午3:46
 */
public class UserStaticProxy implements IUser {

    private UserImpl userImpl;

    public UserStaticProxy(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    /**
     * 登录有前置动作
     */
    @Override
    public void login() {
        doBefore();
        this.userImpl.login();
    }

    /**
     * 更新有前置动作和后置动作
     */
    @Override
    public void updateInfo() {
        doBefore();
        this.userImpl.updateInfo();
        doAfter();
    }

    private void doBefore() {
        System.out.println("静态代理:前置动作……");
    }

    private void doAfter() {
        System.out.println("静态代理:后置动作……");
    }
}
