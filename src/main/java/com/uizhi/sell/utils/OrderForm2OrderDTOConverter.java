package com.uizhi.sell.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.dto.OrderDto;
import com.uizhi.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 16:26
 * @email charles_ji@icloud.com
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
public static OrderDto conver(OrderForm orderForm){
    Gson gson = new Gson();
    OrderDto orderDto = new OrderDto();
    orderDto.setBuyerName(orderForm.getName());
    orderDto.setBuyerPhone(orderForm.getPhone());
    orderDto.setBuyerAddress(orderForm.getAddress());
    orderDto.setBuyerOpenid(orderForm.getOpenid());
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    try {
        orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
    }catch (Exception e){
        log.error("【对象转换】错误，String=",orderForm.getItems());
    }
    orderDto.setOrderDetails(orderDetailList);
    return  orderDto;
}
}
