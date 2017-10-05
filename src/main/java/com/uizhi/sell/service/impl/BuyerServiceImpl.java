package com.uizhi.sell.service.impl;

import com.uizhi.sell.dto.OrderDto;
import com.uizhi.sell.enums.ResultEnum;
import com.uizhi.sell.exception.SellException;
import com.uizhi.sell.service.BuyerService;
import com.uizhi.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 19:46
 * @email charles_ji@icloud.com
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return  checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
       OrderDto orderDto = checkOrderOwner(openid,orderId);
       if(orderDto == null){
           log.error("【取消订单】 查不到改的订单，orderId={}",orderId);
           throw new SellException(ResultEnum.ORDER_NOT_EXIT);
       }
        return orderService.chancel(openid,orderId);
    }

    @Override
    public OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            return  null;
        }
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的openid不一致，openid={},orderid={}",openid,orderId);
            throw  new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
