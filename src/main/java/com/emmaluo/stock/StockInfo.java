package com.emmaluo.stock;

import java.lang.reflect.Field;

/**
 * Created by zcluo on 2016/6/16.
 */
public class StockInfo {
    private boolean isSet;//判断属性是否被设置过，如果都没有被设置不被处理
    private String stockCode;//股票代码
    private String stockCodeB;//B股代码
    private String stockBelongIndexCode;//所属指数代码
    private String tradeCenterCode;//交易所代码
    private String corpName;//公司简称
    private String listDate;//上市日期
    private String convertibleBonds;//可转债
    private String corpFullName;//公司全称
    private String registryAddress;//注册地址
    private String communicationAddress;//通讯地址
    private String legalRepr;//法人代表人
    private String secrOfBOD;//董事会秘书
    private String emailAddress;//email地址
    private String phoneNO;//联系电话
    private String corpUrl;//网址
    private String csrcIndustry;//CSRC行业
    private String sseIndustry;//SSE行业
    private String provice;//所属省份
    private String status;//股票状态
    private String is180Sample;//是否上证180样本
    private String isOversea;//是否海外上市
    private String overSeaLoc;//海外上市地



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

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getConvertibleBonds() {
        return convertibleBonds;
    }

    public void setConvertibleBonds(String convertibleBonds) {
        this.convertibleBonds = convertibleBonds;
    }

    public String getCorpFullName() {
        return corpFullName;
    }

    public void setCorpFullName(String corpFullName) {
        this.corpFullName = corpFullName;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getLegalRepr() {
        return legalRepr;
    }

    public void setLegalRepr(String legalRepr) {
        this.legalRepr = legalRepr;
    }

    public String getSecrOfBOD() {
        return secrOfBOD;
    }

    public void setSecrOfBOD(String secrOfBOD) {
        this.secrOfBOD = secrOfBOD;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getCsrcIndustry() {
        return csrcIndustry;
    }

    public void setCsrcIndustry(String csrcIndustry) {
        this.csrcIndustry = csrcIndustry;
    }

    public String getSseIndustry() {
        return sseIndustry;
    }

    public void setSseIndustry(String sseIndustry) {
        this.sseIndustry = sseIndustry;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs180Sample() {
        return is180Sample;
    }

    public void setIs180Sample(String is180Sample) {
        this.is180Sample = is180Sample;
    }

    public String getIsOversea() {
        return isOversea;
    }

    public void setIsOversea(String isOversea) {
        this.isOversea = isOversea;
    }

    public String getOverSeaLoc() {
        return overSeaLoc;
    }

    public void setOverSeaLoc(String overSeaLoc) {
        this.overSeaLoc = overSeaLoc;
    }

    public String getCorpUrl() {
        return corpUrl;
    }

    public void setCorpUrl(String corpUrl) {
        this.corpUrl = corpUrl;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    @Override
    public String toString() {
        //return super.toString();
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            }
            catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
