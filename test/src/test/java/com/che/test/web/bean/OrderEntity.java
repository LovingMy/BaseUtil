package com.che.test.web.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置项-检测订单
 * <p>
 * 作者：余天然 on 16/5/12 下午12:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderEntity implements Serializable {

    private long id; //检测订单ID

    private String order_num = "";//订单编号

    private int type;// 需求类型：1：竞拍（默认），2：渠道，3：代检，4：寄售

    private int status;//检测状态：0：待分配(默认)，1：已分配待检测，2：已检测待审核，3：已审核，-1：取消

    private String check_address = "";// 检测地址

    private String addTime = "";//创建时间

    private String book_time = "";//预约时间

    private long customerId; //客户ID

    private String customerName = "";//客户名称

    private String mobile = "";//手机号码

    private String mobile_bak = "";//其它联系方式

    private long carId;

    private long car_brand;//品牌

    private long car_series;//车系

    private long car_model;//车型

    private String brand_name = "";//品牌名称
    private String series_name = "";//车系名称
    private String model_name = "";//车型名称

    private long car_year;// 年份

    private String car_plate = "";//车牌

    private String plate_city = "";//车牌所在地

    private String remark = "";//备注信息

}
