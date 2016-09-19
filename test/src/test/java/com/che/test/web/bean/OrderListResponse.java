package com.che.test.web.bean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取检测订单
 * <p>
 * 作者：余天然 on 16/5/12 上午11:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderListResponse extends BaseResponse{

    private int total;//总数

    private List<OrderEntity> orders;//订单数据

}
