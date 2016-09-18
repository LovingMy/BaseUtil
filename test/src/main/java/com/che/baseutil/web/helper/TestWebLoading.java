package com.che.baseutil.web.helper;

import com.che.base_util.LogUtil;

/**
 * 作者：余天然 on 16/9/16 下午7:17
 */
public class TestWebLoading implements IWebLoading{
    @Override
    public void dismiss() {
        LogUtil.print("隐藏进度条");
    }

    @Override
    public void show() {
        LogUtil.print("显示进度条");
    }
}
