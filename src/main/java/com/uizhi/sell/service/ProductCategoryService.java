package com.uizhi.sell.service;

import com.uizhi.sell.dataObject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    public ProductCategory findOne(Integer categoryId);
    public List<ProductCategory> findAll();
    public ProductCategory save(ProductCategory productCategory);
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
