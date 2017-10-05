package com.uizhi.sell.service;

import com.uizhi.sell.dataObject.OrderMaster;
import com.uizhi.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:30
 * @email charles_ji@icloud.com
 */
public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);

    //查询订单
    OrderDto findOne(String orderId);

    //查询订单列表
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderDto chancel(String openId,String orderId);

    //完结订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);
}

