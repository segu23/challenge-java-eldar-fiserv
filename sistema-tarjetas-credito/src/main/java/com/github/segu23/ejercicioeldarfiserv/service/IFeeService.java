package com.github.segu23.ejercicioeldarfiserv.service;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;
import com.github.segu23.ejercicioeldarfiserv.exception.InvalidCardBrandException;

public interface IFeeService {

    double getMoneyAmountWithFee(CardBrand cardBrand, double moneyAmount) throws InvalidCardBrandException;

    double calcCurrentServiceFee(CardBrand cardBrand) throws InvalidCardBrandException;

    double applyFeeLimits(double fee, double minFee, double maxFee);
}
