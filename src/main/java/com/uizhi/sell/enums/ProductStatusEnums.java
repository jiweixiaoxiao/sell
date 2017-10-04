package com.uizhi.sell.enums;

import lombok.Getter;

/**
 * @author jiwei
 * @create 2017-10-04 13:21
 * @email charles_ji@icloud.com
 */
@Getter
public enum  ProductStatusEnums {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer code;
    private  String msg;

    ProductStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
