package com.emmaluo.crawler;

import com.emmaluo.stock.StockInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * Created by zcluo on 2016/6/15.
 */
public class SSEStockListProcessor implements PageProcessor {
    public static final String URL_BASE = "http://biz\\.sse\\.com\\.cn";
    public static final String IND_URL_LIST = "/sseportal/webapp/datapresent/SSEQueryFirstSSEDtlcAct\\?indexCode\\=\\d+&SSECODE=\\d+";
    public static final String STOCK_DETAIL_URL_LIST = "/sseportal/webapp/datapresent/SSEQueryListCmpAct\\?reportName=QueryListCmpRpt&COMPANY_CODE=600247&REPORTTYPE=GSZC&PRODUCTID=600247";
    public static int count = 0;


    private Site site = Site
            .me()
            .setDomain("biz.sse.com.cn")
            .setSleepTime(3000)
            .setCharset("GB2312")
            .setUserAgent(
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");


    @Override
    public void process(Page page) {

        //解析http://biz.sse.com.cn/sseportal/webapp/datapresent/SSEQueryFirstSSEDtlcAct?indexCode=000004&SSECODE=1
        List<String> industryUrls = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table[3]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/a/@href").regex(IND_URL_LIST).all();
        page.addTargetRequests(industryUrls);
        if (page.getUrl().regex(IND_URL_LIST).match()) {
            List<String> indexCodes = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table[3]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/a/@href").regex("/sseportal/webapp/datapresent/SSEQueryFirstSSEDtlcAct\\?indexCode\\=(\\d+)&SSECODE=\\d+").all();
            List<String> sseCodes = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table[3]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/a/@href").regex("/sseportal/webapp/datapresent/SSEQueryFirstSSEDtlcAct\\?indexCode\\=\\d+&SSECODE=(\\d+)").all();
            List<Selectable> rows = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table[3]/tbody/tr[2]/td[2]/table[2]/tbody/tr").nodes();
            List<StockInfo> stockInfos = new ArrayList<StockInfo>();
            List<String> stockdetailUrls = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table[3]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td[1]/a/@href").all();
            rows.remove(0);
            for (Selectable row : rows) {
                StockInfo stockInfo = new StockInfo();
                stockInfo.setStockCode(row.xpath("//td[1]/a/text()").get());
                stockInfo.setCorpName(row.xpath("//td[2]/text()").get());
                stockInfo.setStockBelongIndexCode("A");
                stockInfo.setTradeCenterCode("SH");
                //stockInfo.setStockCode(row.xpath("//td[3]/text()").get());
                stockInfo.setStockCodeB(row.xpath("//td[4]/text()").get());
                stockInfos.add(stockInfo);
            }
            if (stockInfos != null && stockInfos.size() != 0) {
                count += stockInfos.size();
                System.out.println(count);
                page.putField("stockcounts", count);
                page.putField("stockinfos", stockInfos);

            }
        }





    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SSEStockListProcessor()).addUrl("http://biz.sse.com.cn/sseportal/webapp/datapresent/SSEQueryFirstSSENewAct").addPipeline(new FilePipeline("F:/result/"))
                .run();
    }
}
