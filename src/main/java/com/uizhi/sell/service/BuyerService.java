package com.uizhi.sell.service;

import com.uizhi.sell.dto.OrderDto;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 19:43
 * @email charles_ji@icloud.com
 */
public interface BuyerService {
    public OrderDto findOrderOne(String openid,String orderId);
    public OrderDto cancelOrder(String openid,String orderId);
    public OrderDto checkOrderOwner(String openid,String orderId);
}
