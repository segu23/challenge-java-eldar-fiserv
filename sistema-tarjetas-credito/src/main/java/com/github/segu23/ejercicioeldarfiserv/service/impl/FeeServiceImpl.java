package com.github.segu23.ejercicioeldarfiserv.service.impl;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;
import com.github.segu23.ejercicioeldarfiserv.exception.InvalidCardBrandException;
import com.github.segu23.ejercicioeldarfiserv.service.IFeeService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class FeeServiceImpl implements IFeeService {

    @Override
    public double getMoneyAmountWithFee(CardBrand cardBrand, double moneyAmount) throws InvalidCardBrandException {
        return moneyAmount * (calcCurrentServiceFee(cardBrand) / 100 + 1);
    }

    @Override
    public double calcCurrentServiceFee(CardBrand cardBrand) throws InvalidCardBrandException {
        switch (cardBrand) {
            case VISA -> {
                return applyFeeLimits((double) (Calendar.getInstance().get(Calendar.YEAR) % 100) / (Calendar.getInstance().get(Calendar.MONTH) + 1), 0.3, 5);
            }
            case NARA -> {
                return applyFeeLimits((Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) * 0.5, 0.3, 5);
            }
            case AMEX -> {
                return applyFeeLimits((Calendar.getInstance().get(Calendar.MONTH) + 1) * 0.1, 0.3, 5);
            }
            default -> {
                throw new InvalidCardBrandException();
            }
        }
    }

    @Override
    public double applyFeeLimits(double fee, double minFee, double maxFee) {
        if (fee < minFee) return minFee;
        if (fee > maxFee) return maxFee;

        return fee;
    }
}
