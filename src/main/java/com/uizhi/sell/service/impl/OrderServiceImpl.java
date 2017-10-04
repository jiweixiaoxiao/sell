package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.dataObject.OrderMaster;
import com.uizhi.sell.dto.OrderDto;
import com.uizhi.sell.repository.OrderDetailRepository;
import com.uizhi.sell.repository.OrderMasterRepository;
import com.uizhi.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:54
 * @email charles_ji@icloud.com
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDto create(OrderDto orderDto) {
        //查询商品（单价，数量）
       List<OrderDetail> orderDetailList =  orderDto.getOrderDetailsList();

        //计算总价

        //写入订单数据库 OrderMaster和OrderDetail

        //扣库存
        return null;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public List<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto chancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
