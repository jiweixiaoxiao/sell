package com.uizhi.sell.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;
@Slf4j
public class UUIDUtilTest {
    @Test
    public void getUuid() throws Exception {
        log.info(UUIDUtil.getUuid());
    }

}