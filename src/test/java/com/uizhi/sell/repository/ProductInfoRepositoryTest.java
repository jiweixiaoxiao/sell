package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Test
    public void save(){
        /**
         * private String productId;
         private String productName;
         private BigDecimal productPrice;
         private Integer productStock;
         private String productDescription;
         private String productIcon;
         private Integer productStatus;
         private Integer categoryType;
         private Date createTime;
         private Date updateTime;
         */
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("鱼香肉丝");
        productInfo.setProductPrice(new BigDecimal(12.5));
        productInfo.setProductStock(2);
        productInfo.setProductDescription("好吃");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus(){
       List<ProductInfo> list =  productInfoRepository.findByProductStatus(0);
       Assert.assertNotEquals(0,list.size());
    }
}
