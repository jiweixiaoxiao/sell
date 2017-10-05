package com.uizhi.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.enums.OrderStatusEnum;
import com.uizhi.sell.enums.PayStatusEnum;
import com.uizhi.sell.utils.serializer.Date2LongSerializer;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    private Date updateTime;
    List<OrderDetail> orderDetails;
}
