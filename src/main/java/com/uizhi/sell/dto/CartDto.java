package com.uizhi.sell.dto;

import lombok.Data;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 10:50
 * @email charles_ji@icloud.com
 */
@Data
public class CartDto {
    private String productId;
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
