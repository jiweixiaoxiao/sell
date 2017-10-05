package com.uizhi.sell.utils;

import java.util.UUID;

/**
 * DESCRIPTION :
 *
 * @author jiwei
 * @create 2017-10-05 10:37
 * @email charles_ji@icloud.com
 */
public class UUIDUtil {
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
