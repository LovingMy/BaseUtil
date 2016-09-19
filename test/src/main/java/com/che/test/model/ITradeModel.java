package com.che.test.model;

import com.che.test.bean.ExtensionRequest;
import com.che.test.bean.ExtensionResponse;

import rx.Observable;

/**
 * 交易模块
 * <p>
 * 作者：余天然 on 16/9/19 下午12:03
 */
public interface ITradeModel {

    /***
     * 1小时内是否联系了车主
     *
     * @return
     */
    Observable<Boolean> checkIsContact();

    /***
     * 4小时内是否确认看车
     *
     * @return
     */
    Observable<Boolean> checkIsLookCar();

    /***
     * 24小时(+延期时间)内是否确认成交
     *
     * @return
     */
    Observable<Boolean> checkIsSuccess();

    /**
     * 申请延期
     *
     * @return
     */
    Observable<ExtensionResponse> applyExtension(ExtensionRequest request);

}
