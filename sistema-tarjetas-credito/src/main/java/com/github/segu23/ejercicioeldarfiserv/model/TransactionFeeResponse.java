package com.github.segu23.ejercicioeldarfiserv.model;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;

public class TransactionFeeResponse {

    private CardBrand cardBrand;

    private double moneyAmount;

    private double moneyAmountWithFees;

    private String actualFeePercentage;

    public TransactionFeeResponse(CardBrand cardBrand, double moneyAmount, double moneyAmountWithFees, String actualFeePercentage) {
        this.cardBrand = cardBrand;
        this.moneyAmount = moneyAmount;
        this.moneyAmountWithFees = moneyAmountWithFees;
        this.actualFeePercentage = actualFeePercentage;
    }

    public TransactionFeeResponse() {
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public double getMoneyAmountWithFees() {
        return moneyAmountWithFees;
    }

    public void setMoneyAmountWithFees(double moneyAmountWithFees) {
        this.moneyAmountWithFees = moneyAmountWithFees;
    }

    public String getActualFeePercentage() {
        return actualFeePercentage;
    }

    public void setActualFeePercentage(String actualFeePercentage) {
        this.actualFeePercentage = actualFeePercentage;
    }
}
