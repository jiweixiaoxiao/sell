package com.uizhi.sell.service.impl;

import com.uizhi.sell.dataObject.ProductInfo;
import com.uizhi.sell.dto.CartDto;
import com.uizhi.sell.enums.ResultEnum;
import com.uizhi.sell.exception.ResultException;
import com.uizhi.sell.repository.ProductInfoRepository;
import com.uizhi.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 13:33
 * @email charles_ji@icloud.com
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll(Integer productStatus) {
        return productInfoRepository.findByProductStatus(productStatus);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new ResultException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtos) {
        for(CartDto cartDto : cartDtos){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new ResultException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result<0){
                throw new ResultException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
