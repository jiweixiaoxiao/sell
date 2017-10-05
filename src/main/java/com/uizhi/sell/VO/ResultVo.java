package com.uizhi.sell.VO;

import lombok.Data;

import java.util.List;

/**
 * DESCRIPTION :构造结果集的类 最外层
 *
 * @author jiwei
 * @create 2017-10-04 16:45
 * @email charles_ji@icloud.com
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private  String msg;
    private Object data;
}
