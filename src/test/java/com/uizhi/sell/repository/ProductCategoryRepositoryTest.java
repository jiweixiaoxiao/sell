package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

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
    @Transactional
    public void addProductCategoryR(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("今日特价");
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateProductCategoryR(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("男生最爱");
        productCategoryRepository.save(productCategory);

    }

    @Test
    public void findByCategoryType(){
        List<Integer> list = Arrays.asList(1,2);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(list);
        System.out.println(productCategoryList);
    }

}