package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
       ProductCategory productCategory =  productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void addProductCategoryR(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("今日特价");
        productCategory.setCategoryType(2);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateProductCategoryR(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("男生最爱");
        productCategoryRepository.save(productCategory);
    }

}