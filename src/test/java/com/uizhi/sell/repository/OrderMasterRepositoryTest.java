package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void findByBuyerOpenid() throws Exception {
    }

    @Test
    public void findOne(){
       OrderMaster orderMaster =  orderMasterRepository.findOne("1");
        System.out.println(orderMaster.toString());
    }

}