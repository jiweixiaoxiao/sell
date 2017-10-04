package com.uizhi.sell.repository;

import com.uizhi.sell.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    public List<ProductInfo> findByProductStatus(Integer productStatus);
}
