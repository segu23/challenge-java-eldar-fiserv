package com.github.segu23.ejercicioeldarfiserv.model;

public class TransactionFeeRequest {

    private String cardBrandName;

    private double moneyAmount;

    public TransactionFeeRequest(String cardBrandName, double moneyAmount) {
        this.cardBrandName = cardBrandName;
        this.moneyAmount = moneyAmount;
    }

    public TransactionFeeRequest() {
    }

    public String getCardBrandName() {
        return cardBrandName;
    }

    public void setCardBrandName(String cardBrandName) {
        this.cardBrandName = cardBrandName;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
