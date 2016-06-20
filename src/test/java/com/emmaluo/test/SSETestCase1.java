package com.emmaluo.test;


import com.emmaluo.crawler.SSEStockListProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zcluo on 2016/6/20.
 */
public class SSETestCase1 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-*.xml");
        final SSEStockListProcessor sseStockListProcessor = applicationContext.getBean(SSEStockListProcessor.class);
        sseStockListProcessor.crawl();
    }
}
