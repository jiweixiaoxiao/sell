package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.enums.ProductStatusEnums;
import com.uizhi.sell.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productInfoService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() throws Exception {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"productPrice");
        Sort sort = new Sort(order);
        Pageable page = new PageRequest(0,2,sort);
        Page<ProductInfo> pageList = productInfoService.findAll(page);
        System.out.println(pageList.getContent());
        Assert.assertNotEquals(0,pageList.getTotalElements());
    }

    @Test
    public void findUpAll() throws Exception {
       List<ProductInfo> list =  productInfoService.findUpAll(ProductStatusEnums.UP.getCode());
       Assert.assertNotEquals(0,list.size());
    }

    @Test
    @Transactional
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("323456");
        productInfo.setProductName("红烧猪蹄");
        productInfo.setProductPrice(new BigDecimal(32.5));
        productInfo.setProductStock(1);
        productInfo.setProductDescription("好油腻");
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
        productInfo.setCategoryType(2);
        productInfoService.save(productInfo);
    }

}