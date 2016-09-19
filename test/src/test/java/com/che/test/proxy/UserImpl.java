package com.che.test.proxy;

/**
 * 用户接口实现类
 * <p>
 * 作者：余天然 on 16/9/18 下午3:45
 */
public class UserImpl implements IUser {

    @Override
    public void login() {
        System.out.println("小明在登录……");

    }

    @Override
    public void updateInfo() {
        System.out.println("小明在更新信息……");
    }
}
