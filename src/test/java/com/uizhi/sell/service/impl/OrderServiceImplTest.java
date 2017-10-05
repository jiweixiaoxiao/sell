package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.OrderDetail;
import com.uizhi.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    private  final String ORDER_ID = "b2beb964905e435e9ecefd89d14817fa";
    private  final  String BUYEROPEN_ID = "ew3euwhd7sjw9diwkq";
    @Autowired
    OrderServiceImpl orderService;
    @Test
    public void create() throws Exception {
        /*
        name: "张三"
  phone: "18868822111"
  address: "慕课网总部"
  openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
  items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]

         */
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerAddress("地球村");
        orderDto.setBuyerOpenid("ew3euwhd7sjw9diwkq");
        orderDto.setBuyerPhone("18868822111");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(2);


        OrderDto orderDto2 = new OrderDto();
        orderDto2.setBuyerName("张三");
        orderDto2.setBuyerAddress("地球村");
        orderDto2.setBuyerOpenid("ew3euwhd7sjw9diwkq");
        orderDto2.setBuyerPhone("18868822111");
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("223456");
        orderDetail2.setProductQuantity(5);
        orderDto.setOrderDetails(Arrays.asList(orderDetail,orderDetail2));
        orderService.create(orderDto);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        log.info("查询单个订单，result={}",orderDto);
        Assert.assertEquals(ORDER_ID,orderDto.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYEROPEN_ID,pageRequest);
        log.info("查询订单列表 ，result={}",orderDtoPage);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void chancel() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(ORDER_ID);
        orderDto.setBuyerOpenid(BUYEROPEN_ID);
        //orderService.chancel(orderDto);
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}