package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.ProductCategory;
import com.uizhi.sell.repository.ProductCategoryRepository;
import com.uizhi.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
   private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return productCategoryRepository.findByCategoryTypeIn(list);
    }
}
