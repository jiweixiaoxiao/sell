package com.uizhi.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIT(10,"商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"库存不足"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单信息不存在"),
    ORDER_EXCEPTION(14,"订单状态异常"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    PAIED_ERROR(16,"支付失败"),
    CART_EMPTY(17,"购物车为空"),
    ORDER_OWNER_ERROR(18,"请重新登录")
    ;

    private  Integer code;
    private  String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
