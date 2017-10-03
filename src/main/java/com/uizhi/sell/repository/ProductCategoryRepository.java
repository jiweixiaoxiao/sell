package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
