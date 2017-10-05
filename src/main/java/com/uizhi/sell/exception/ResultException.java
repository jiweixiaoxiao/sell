package com.uizhi.sell.exception;

import com.uizhi.sell.enums.ResultEnum;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 11:15
 * @email charles_ji@icloud.com
 */
public class ResultException extends RuntimeException{
    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
