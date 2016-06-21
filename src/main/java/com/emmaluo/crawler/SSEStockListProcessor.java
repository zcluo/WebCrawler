package com.emmaluo.crawler;

import com.emmaluo.model.StockInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zcluo on 2016/6/15.
 */
@Component
public class SSEStockListProcessor implements PageProcessor {

    @Resource
    private Pipeline stockInfoDaoPipeline;

    public static final String URL_BASE = "http://biz\\.sse\\.com\\.cn";
    //股票行业信息
    public static final String IND_URL_LIST = "/sseportal/webapp/datapresent/SSEQueryFirstSSEDtlcAct\\?indexCode\\=\\d+&SSECODE=\\d+";
    //股票详细信息
    public static final String STOCK_DETAIL_URL_LIST = "/sseportal/webapp/datapresent/SSEQueryListCmpAct\\?reportName=QueryListCmpRpt&REPORTTYPE=\\w+&PRODUCTID=\\d+&COMPANY_CODE=\\d+";

    //股票列表
    public static final String STOCK_LIST_URL_LIST="/sseportal/webapp/datapresent/SSEQueryStockInfoAct\\?reportName=BizCompStockInfoRpt&PRODUCTID=&PRODUCTJP=&PRODUCTNAME=&keyword=&tab_flg=1&CURSOR=\\d+";
    public static int count = 0;


    private Site site = Site
            .me()
            .setDomain("biz.sse.com.cn")
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
        //股票概要信息处理
        if(page.getUrl().regex(STOCK_DETAIL_URL_LIST).match()){
            //获取第一部分
            StockInfo stockInfo = null;

            List<Selectable> rows1 = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td/table/tbody/tr").nodes();
            stockInfo = new StockInfo();
            stockInfo.setSet(false);
            for(Selectable row : rows1){

                String td2 = row.xpath("//td[2]/text()").get();
                td2 = td2 != null?td2.trim():null;
                switch (row.xpath("//td[1]/text()").get().trim()){

                    case "股票代码(A股/B股):":
                        String code = td2.trim();
                        String[] codes = code.split("/");
                        String codeA = codes[0];
                        String codeB = codes[1];

                        stockInfo.setStockcode(codeA);
                        stockInfo.setStockcodeb(codeB);
                        stockInfo.setSet(true);
                        break;
                    case "上市日(A股/B股):":
                        //xpath不对需要重新获取
                        String date = row.xpath("//td[2]/span/a/text()").get();
                        stockInfo.setListdate(date);
                        stockInfo.setSet(true);
                        break;
                    case "可转债简称（代码）:":
                        stockInfo.setConvertiblebonds(td2);
                        stockInfo.setSet(true);
                        break;
                    case "公司简称(中/英):":
                        stockInfo.setCorpname(td2);
                        stockInfo.setSet(true);
                        break;
                    case "公司全称(中/英):":
                        stockInfo.setCorpfullname(td2);
                        stockInfo.setSet(true);
                        break;
                    case "注册地址:":
                        stockInfo.setRegistryaddress(td2);
                        stockInfo.setSet(true);
                        break;
                    case "通讯地址（邮编）:":
                        stockInfo.setCommunicationaddress(td2);
                        stockInfo.setSet(true);
                        break;

                    default:
                }

            }
            //获取第二部分
            List<Selectable> rows2 = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table/tbody/tr/td/table/tbody/tr/td[1]/table[1]/tbody/tr[2]/td[2]/table[2]/tbody/tr[1]/td/table/tbody/tr").nodes();
            if(stockInfo == null) {
                stockInfo = new StockInfo();
                stockInfo.setSet(false);
            }
            for(Selectable row : rows2){

                String td2 = row.xpath("//td[2]/text()").get();
                td2 = td2 != null?td2.trim():null;
                switch (row.xpath("//td[1]/text()").get().trim()){
                    case "法定代表人:":
                        stockInfo.setLegalrepr(td2);
                        stockInfo.setSet(true);
                        break;
                    case "董事会秘书姓名:":
                        stockInfo.setSecrofbod(td2);
                        stockInfo.setSet(true);
                        break;
                    case "E-mail:":

                        String email = row.xpath("//td[2]/a/text()").get();
                        stockInfo.setEmailaddress(email);
                        stockInfo.setSet(true);
                        break;
                    case "联系电话:":
                        stockInfo.setPhoneno(td2);
                        stockInfo.setSet(true);
                        break;
                    case "网址:":

                        String corpUrl = row.xpath("//td[2]/a/text()").get();
                        stockInfo.setCorpurl(corpUrl);
                        stockInfo.setSet(true);
                        break;
                    case "CSRC行业 (门类/大类/中类):":
                        stockInfo.setCsrcindustry(td2);
                        stockInfo.setSet(true);
                        break;
                    case "SSE行业:":
                        stockInfo.setSseindustry(td2);
                        stockInfo.setSet(true);
                        break;
                    case "所属省/直辖市:":
                        stockInfo.setProvice(td2);
                        stockInfo.setSet(true);
                        break;
                    case "A股状态/B股状态:":
                        stockInfo.setStatus(td2);
                        stockInfo.setSet(true);
                        break;
                    case "是否上证180样本股:":
                        stockInfo.setIs180sample(td2);
                        stockInfo.setSet(true);
                        break;
                    case "是否境外上市:":
                        stockInfo.setIsoversea(td2);
                        stockInfo.setSet(true);
                        break;
                    case "境外上市地:":
                        stockInfo.setOversealoc(td2);
                        stockInfo.setSet(true);
                        break;
                    default:
                }
            }
            if(stockInfo != null && stockInfo.getStockcode() != null && stockInfo.isSet()){
                page.putField("stockInfo",stockInfo);

            }

        }
        //股票列表信息处理
        if(page.getUrl().regex(STOCK_LIST_URL_LIST).match()){
            //TODO 获取股票列表并且把股票概要信息URL加入到targetrequest中
            //增加股票详细信息URL到爬取目标请求
            List<String> detailurls = page.getHtml().links().regex(STOCK_DETAIL_URL_LIST).all();
            page.addTargetRequests(detailurls);
            //增加下一个分页的股票列表URL到爬取目标请求
            List<Selectable> hrefs = page.getHtml().xpath("html/body/table[3]/tbody/tr/td[4]/table[2]/tbody/tr/td/table/tbody/tr[2]/td[2]/table[5]/tbody/tr/td[2]/a").nodes();
            for(Selectable href : hrefs){
                if(href.xpath("//a/text()").get().equals("下一页"))
                {
                    page.addTargetRequest(href.xpath("//a//@href").get());
                }
            }

        }






    }

    @Override
    public Site getSite() {
        return site;
    }

    public void crawl() {
        /*Spider.create(new SSEStockListProcessor()).addUrl("http://biz.sse.com.cn/sseportal/webapp/datapresent/SSEQueryFirstSSENewAct").addPipeline(new FilePipeline("F:/result/"))
                .run();*/
        Spider.create(new SSEStockListProcessor()).addUrl("http://biz.sse.com.cn/sseportal/webapp/datapresent/SSEQueryStockInfoAct?reportName=BizCompStockInfoRpt&PRODUCTID=&PRODUCTJP=&PRODUCTNAME=&keyword=&tab_flg=1&CURSOR=1").addPipeline(stockInfoDaoPipeline).addPipeline(new FilePipeline("F:/result/")).thread(10)
                .run();



    }
}
