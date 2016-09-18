package com.che.baseutil.web.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取检测订单
 * <p>
 * 作者：余天然 on 16/5/12 下午12:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderListRequest {

    private String id; //检测订单ID

    private String customer_id;// 客户ID

    private String check_car_id;// 检测车辆ID

    private String order_num;// 检测订单编号

    private String keyword;// 关键字

    private String type;// 需求类型：1：竞拍（默认），2：渠道，3：代检，就是车商，4：寄售

    private String startTime;//开始创建时间

    private String endTime;//结束创建时间

    private String status;//检测状态：0：待分配，1：已分配待检测，2：已检测待审核，3：已审核，-1：取消;多个状态逗号分隔，默认为1，2，3

    private Integer order;//排序：0创建时间desc;1创建时间asc；2更新时间desc;3更新时间asc；4预约时间desc；5预约时间asc;null默认按待检测，预约时间desc

    private Integer start;//开始位置

    private Integer pagesize;//每页条数

}
