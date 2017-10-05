package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenidAndOrderId
                        ("ew3euwhd7sjw9diwkq",
                                "b17a2b20211a4cd8a8bfb3ca1f3c5169"
                                ,pageRequest);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }

    @Test
    public void findOne(){
       OrderMaster orderMaster =  orderMasterRepository.findOne("1");
        System.out.println(orderMaster.toString());
    }

}