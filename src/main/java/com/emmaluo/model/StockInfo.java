package com.emmaluo.model;

import java.lang.reflect.Field;

public class StockInfo {
    private boolean isSet;

    private String stockcode;

    private String stockcodeb;

    private String stockbelongindexcode;

    private String tradecentercode;

    private String corpname;

    private String listdate;

    private String convertiblebonds;

    private String corpfullname;

    private String registryaddress;

    private String communicationaddress;

    private String legalrepr;

    private String secrofbod;

    private String emailaddress;

    private String phoneno;

    private String corpurl;

    private String csrcindustry;

    private String sseindustry;

    private String provice;

    private String status;

    private String is180sample;

    private String isoversea;

    private String oversealoc;

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode == null ? null : stockcode.trim();
    }

    public String getStockcodeb() {
        return stockcodeb;
    }

    public void setStockcodeb(String stockcodeb) {
        this.stockcodeb = stockcodeb == null ? null : stockcodeb.trim();
    }

    public String getStockbelongindexcode() {
        return stockbelongindexcode;
    }

    public void setStockbelongindexcode(String stockbelongindexcode) {
        this.stockbelongindexcode = stockbelongindexcode == null ? null : stockbelongindexcode.trim();
    }

    public String getTradecentercode() {
        return tradecentercode;
    }

    public void setTradecentercode(String tradecentercode) {
        this.tradecentercode = tradecentercode == null ? null : tradecentercode.trim();
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname == null ? null : corpname.trim();
    }

    public String getListdate() {
        return listdate;
    }

    public void setListdate(String listdate) {
        this.listdate = listdate == null ? null : listdate.trim();
    }

    public String getConvertiblebonds() {
        return convertiblebonds;
    }

    public void setConvertiblebonds(String convertiblebonds) {
        this.convertiblebonds = convertiblebonds == null ? null : convertiblebonds.trim();
    }

    public String getCorpfullname() {
        return corpfullname;
    }

    public void setCorpfullname(String corpfullname) {
        this.corpfullname = corpfullname == null ? null : corpfullname.trim();
    }

    public String getRegistryaddress() {
        return registryaddress;
    }

    public void setRegistryaddress(String registryaddress) {
        this.registryaddress = registryaddress == null ? null : registryaddress.trim();
    }

    public String getCommunicationaddress() {
        return communicationaddress;
    }

    public void setCommunicationaddress(String communicationaddress) {
        this.communicationaddress = communicationaddress == null ? null : communicationaddress.trim();
    }

    public String getLegalrepr() {
        return legalrepr;
    }

    public void setLegalrepr(String legalrepr) {
        this.legalrepr = legalrepr == null ? null : legalrepr.trim();
    }

    public String getSecrofbod() {
        return secrofbod;
    }

    public void setSecrofbod(String secrofbod) {
        this.secrofbod = secrofbod == null ? null : secrofbod.trim();
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress == null ? null : emailaddress.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getCorpurl() {
        return corpurl;
    }

    public void setCorpurl(String corpurl) {
        this.corpurl = corpurl == null ? null : corpurl.trim();
    }

    public String getCsrcindustry() {
        return csrcindustry;
    }

    public void setCsrcindustry(String csrcindustry) {
        this.csrcindustry = csrcindustry == null ? null : csrcindustry.trim();
    }

    public String getSseindustry() {
        return sseindustry;
    }

    public void setSseindustry(String sseindustry) {
        this.sseindustry = sseindustry == null ? null : sseindustry.trim();
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice == null ? null : provice.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIs180sample() {
        return is180sample;
    }

    public void setIs180sample(String is180sample) {
        this.is180sample = is180sample == null ? null : is180sample.trim();
    }

    public String getIsoversea() {
        return isoversea;
    }

    public void setIsoversea(String isoversea) {
        this.isoversea = isoversea == null ? null : isoversea.trim();
    }

    public String getOversealoc() {
        return oversealoc;
    }

    public void setOversealoc(String oversealoc) {
        this.oversealoc = oversealoc == null ? null : oversealoc.trim();
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