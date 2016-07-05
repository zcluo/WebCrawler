package com.emmaluo.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcluo on 2016/7/5.
 */
@Component
public class SZSEStockListProcessor implements PageProcessor {

    @Resource
    private Pipeline stockInfoDaoPipeline;

    public static final String URL_BASE = "http://www\\.szse\\.cn";

    public static final String START_URL = "http://www.szse.cn/main/marketdata/jypz/colist/";

    public static final String STOCK_LIST =  "/szseWeb/ShowReport.szse?CATALOGID=1110&tab1PAGECOUNT=179&ENCODE=1&TABKEY=tab1&tab1RECORDCOUNT=1781&tab1PAGENUM=";

    private Site site = Site
            .me()
            .setDomain("www.szse.cn")
            .setSleepTime(2000)
            .setTimeOut(3000)
            .setRetryTimes(8)
            .setRetrySleepTime(2000)
            .setCycleRetryTimes(5) //只有该方法的重试生效
            .setCharset("GB2312")
            .setUserAgent(
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");



    @Override
    public void process(Page page) {
        int totolPage = 0;
        if(page.getUrl().regex(START_URL).match()){
            String pageNums = page.getHtml().xpath("//span[@id='REPORT_ID_1110']/table/tbody/tr/td/table[10]/tbody/tr/td[1]").regex("共(\\d+)页").get();
            totolPage = Integer.parseInt(pageNums);
            if(totolPage != 0){
                List<String> targetUrls = new ArrayList<String>();
                for(int i = 1; i <= totolPage; i++){
                    String url = STOCK_LIST + i;
                    targetUrls.add(url);

                }
                page.addTargetRequests(targetUrls);
            }
        }

        page.putField("content",page.getHtml());


    }

    @Override
    public Site getSite() {
        return site;
    }

    public void crawl(){
        Spider.create(new SZSEStockListProcessor()).addUrl("http://www.szse.cn/main/marketdata/jypz/colist/").addPipeline(stockInfoDaoPipeline).addPipeline(new FilePipeline("F:/result/")).thread(10)
                .run();
    }
}
