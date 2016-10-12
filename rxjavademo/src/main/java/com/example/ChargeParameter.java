package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yakun on 2016/9/27.
 */

public class ChargeParameter {


    private String diagnosisId;
    private String chargeDate;
    private String prescriptionMoney;
    private String discountMoney;
    private String amountMoney;
    private String collectionMoney;
    private String debtsMoney;

    private ArrayList<Price> List;

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getPrescriptionMoney() {
        return prescriptionMoney;
    }

    public void setPrescriptionMoney(String prescriptionMoney) {
        this.prescriptionMoney = prescriptionMoney;
    }

    public String getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(String discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(String amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getCollectionMoney() {
        return collectionMoney;
    }

    public void setCollectionMoney(String collectionMoney) {
        this.collectionMoney = collectionMoney;
    }

    public String getDebtsMoney() {
        return debtsMoney;
    }

    public void setDebtsMoney(String debtsMoney) {
        this.debtsMoney = debtsMoney;
    }

    public ArrayList<Price> getList() {
        return List;
    }

    public void setList(ArrayList<Price> list) {
        List = list;
    }

    public static class Price {
        private String otcId;
        private String saleUnitPrice;

        public String getOtcId() {
            return otcId;
        }

        public void setOtcId(String otcId) {
            this.otcId = otcId;
        }

        public String getSaleUnitPrice() {
            return saleUnitPrice;
        }

        public void setSaleUnitPrice(String saleUnitPrice) {
            this.saleUnitPrice = saleUnitPrice;
        }
    }
}
