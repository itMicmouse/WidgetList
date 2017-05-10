package com.hospital.main.domain;

/**
 * Created by yakun on 2017/1/6.
 */

public class PrescribeItemDefault {
    private String id;
    private String commodityCategory;
    private String diagnosisId;
    private String commodityId;
    private String groupId;
    private String usage;
    private String useLevel;
    private String frequency;
    private String priceUnit;
    private String totalNumber;
    private String receivablePrice;//应收
    private String isSetMethod;
    private String isBlank;
    private String showCommodityName;
    private String commodityName;
    private String useLevelUnit;
    private String specifcations;
    private String docterOrder;
    private String dosageFormId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityCategory() {
        return commodityCategory;
    }

    public void setCommodityCategory(String commodityCategory) {
        this.commodityCategory = commodityCategory;
    }

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUseLevel() {
        return useLevel;
    }

    public void setUseLevel(String useLevel) {
        this.useLevel = useLevel;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getReceivablePrice() {
        return receivablePrice;
    }

    public void setReceivablePrice(String receivablePrice) {
        this.receivablePrice = receivablePrice;
    }

    public String getIsSetMethod() {
        return isSetMethod;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setIsSetMethod(String isSetMethod) {
        this.isSetMethod = isSetMethod;
    }

    public String getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(String isBlank) {
        this.isBlank = isBlank;
    }

    public String getShowCommodityName() {
        return showCommodityName;
    }

    public void setShowCommodityName(String showCommodityName) {
        this.showCommodityName = showCommodityName;
    }

    public String getUseLevelUnit() {
        return useLevelUnit;
    }

    public void setUseLevelUnit(String useLevelUnit) {
        this.useLevelUnit = useLevelUnit;
    }

    public String getSpecifcations() {
        return specifcations;
    }

    public void setSpecifcations(String specifcations) {
        this.specifcations = specifcations;
    }

    public String getDocterOrder() {
        return docterOrder;
    }

    public void setDocterOrder(String docterOrder) {
        this.docterOrder = docterOrder;
    }

    public String getDosageFormId() {
        return dosageFormId;
    }

    public void setDosageFormId(String dosageFormId) {
        this.dosageFormId = dosageFormId;
    }

}
