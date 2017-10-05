package com.uizhi.sell.utils;

import com.uizhi.sell.dataObject.OrderMaster;
import com.uizhi.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 13:17
 * @email charles_ji@icloud.com
 */
public class OrderMaster2OrderDTO {
    public static OrderDto conver(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
        for(OrderMaster orderMaster : orderMasterList){
            orderDtoList.add(conver(orderMaster));
        }
        return orderDtoList;
    }
}
