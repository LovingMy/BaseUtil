package com.che.test.rxjava.asyncwork.test;

import java.util.List;

/**
 * 第三方接口
 */
public interface Api {

    //只提供了同步方式的接口
    List<News> getNewsList(String tag);//获取新闻列表

    Uri save(News news);//保存新闻得到URI
}
