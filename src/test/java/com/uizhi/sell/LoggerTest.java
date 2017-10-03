package com.uizhi.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void testLog(){
        String name = "abc";
        int age = 11;
        log.debug("debug.......");
        log.info("name:{},age:{}",name,age);
        log.error("error.......");
    }
}
