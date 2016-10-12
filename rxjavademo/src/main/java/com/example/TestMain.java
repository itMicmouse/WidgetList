package com.example;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by yakun on 2016/9/27.
 */

public class TestMain {
    public static void main(String[] args) {
        ChargeParameter chargeParameter = new ChargeParameter();
        chargeParameter.setAmountMoney("220");
        chargeParameter.setChargeDate("flskdjflk");
        chargeParameter.setCollectionMoney("23432");
        chargeParameter.setDebtsMoney("2424");
        chargeParameter.setDiagnosisId("skdljflksdjflkjsd");
        chargeParameter.setDiscountMoney("4000");
        chargeParameter.setPrescriptionMoney("8000");
        ArrayList<ChargeParameter.Price> prices = new ArrayList<>();
        ChargeParameter.Price price =  null;
        for (int i = 0; i < 5; i++) {
            price =  new ChargeParameter.Price();
            price.setOtcId("lsdjfkljsdfkljsdlfjs");
            price.setSaleUnitPrice("333");
            prices.add(price);
        }
        chargeParameter.setList(prices);

        Gson gson = new Gson();
        String s = gson.toJson(chargeParameter);
        System.out.println(s);
    }
}
