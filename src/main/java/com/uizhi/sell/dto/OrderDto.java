package com.uizhi.sell.dto;

import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.enums.OrderStatusEnum;
import com.uizhi.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:46
 * @email charles_ji@icloud.com
 */
@Data
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
    List<OrderDetail> orderDetailsList;
}
