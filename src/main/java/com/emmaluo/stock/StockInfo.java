package com.emmaluo.stock;

/**
 * Created by zcluo on 2016/6/16.
 */
public class StockInfo {
    private String stockCode;
    private String stockCodeB;
    private String stockBelongIndexCode;
    private String tradeCenterCode;
    private String corpName;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockCodeB() {
        return stockCodeB;
    }

    public void setStockCodeB(String stockCodeB) {
        this.stockCodeB = stockCodeB;
    }

    public String getStockBelongIndexCode() {
        return stockBelongIndexCode;
    }

    public void setStockBelongIndexCode(String stockBelongIndexCode) {
        this.stockBelongIndexCode = stockBelongIndexCode;
    }

    public String getTradeCenterCode() {
        return tradeCenterCode;
    }

    public void setTradeCenterCode(String tradeCenterCode) {
        this.tradeCenterCode = tradeCenterCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    @Override
    public String toString() {
        //return super.toString();
        return "公司名称： "+this.corpName+"\n"+"股票代码： "+this.stockCode+"\n"+"B股代码： "+this.stockCodeB+"\n"+"交易中心： "+this.tradeCenterCode+"\n"+"指数： "+this.stockBelongIndexCode+"\n";
    }
}
