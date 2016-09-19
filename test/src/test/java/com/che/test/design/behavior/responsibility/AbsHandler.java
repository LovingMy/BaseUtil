package com.che.test.design.behavior.responsibility;

/**
 * 作者：余天然 on 16/9/12 下午9:41
 */
public class AbsHandler implements IHandler {

    private IHandler nextHandler;

    public IHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(IHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {

    }

    public void elseTodo(String request) {
        IHandler nextHandler = getNextHandler();
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            superTodo(request);
        }
    }

    public void superTodo(String type) {
        System.out.println("我是中国处理中心，处理各省处理不了的事务");
    }
}
