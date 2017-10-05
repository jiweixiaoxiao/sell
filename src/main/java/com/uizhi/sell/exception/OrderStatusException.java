package com.uizhi.sell.exception;

import com.uizhi.sell.enums.OrderStatusEnum;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 14:06
 * @email charles_ji@icloud.com
 */
public class OrderStatusException extends RuntimeException{
    private Integer code;

    public OrderStatusException(OrderStatusEnum orderStatusEnum) {
        super(orderStatusEnum.getMsg());
        this.code = orderStatusEnum.getCode();
    }
}
