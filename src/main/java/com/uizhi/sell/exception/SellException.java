package com.uizhi.sell.exception;

import com.uizhi.sell.enums.ResultEnum;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 23:33
 * @email charles_ji@icloud.com
 */
public class SellException extends RuntimeException{
private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
