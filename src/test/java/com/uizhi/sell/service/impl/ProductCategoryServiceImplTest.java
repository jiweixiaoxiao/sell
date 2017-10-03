package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = productCategoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() throws Exception {
       ProductCategory productCategory =  new ProductCategory("妇女最爱",3);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategoryList.size());
    }

}