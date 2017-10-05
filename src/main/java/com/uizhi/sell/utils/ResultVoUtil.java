package com.uizhi.sell.utils;

import com.uizhi.sell.VO.ResultVo;

import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-04 17:13
 * @email charles_ji@icloud.com
 */
public class ResultVoUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return  resultVo;
    }

    public  static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(null);
        return  resultVo;
    }

    public static  ResultVo error(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg("失败");
        return resultVo;
    }
}
