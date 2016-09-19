package com.che.baseutil.model;

import com.che.baseutil.bean.CaptureOrderRequest;
import com.che.baseutil.bean.CaptureOrderResponse;
import com.che.baseutil.bean.TopupRequest;
import com.che.baseutil.bean.TopupResponse;

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
