package com.emmaluo.test;

import com.emmaluo.crawler.SZSEStockListProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;



/**
 * Created by zcluo on 2016/7/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class SZSETest {
    @Resource
    private SZSEStockListProcessor szseStockListProcessor;

    @Test
    public void testCrawl(){
        szseStockListProcessor.crawl();
    }


}
