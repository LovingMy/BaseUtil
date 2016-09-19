package com.che.test.model;

import com.che.test.bean.CaptureOrderRequest;
import com.che.test.bean.CaptureOrderResponse;
import com.che.test.bean.TopupRequest;
import com.che.test.bean.TopupResponse;

import rx.Observable;

/**
 * 抢单模块
 * <p>
 * 作者：余天然 on 16/9/19 上午11:37
 */
public interface ICaptureOrderModel {

    /**
     * 抢单
     */
    Observable<CaptureOrderResponse> captureOrder(CaptureOrderRequest request);

    /**
     * 充值
     */
    Observable<TopupResponse> topup(TopupRequest request);

}
