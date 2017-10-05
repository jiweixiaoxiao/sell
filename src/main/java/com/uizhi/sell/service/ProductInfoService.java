package com.uizhi.sell.service;

import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    //查找一个ProductInfo
    public ProductInfo findOne(String productId);
    //查找所有
    public Page<ProductInfo> findAll(Pageable pageable);
    //查找当前上架的所有商品
    public List<ProductInfo> findUpAll(Integer productStatus);
    //保存一个商品
    public ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //减库存
    void decreaseStock(List<CartDto> cartDtos);



}
