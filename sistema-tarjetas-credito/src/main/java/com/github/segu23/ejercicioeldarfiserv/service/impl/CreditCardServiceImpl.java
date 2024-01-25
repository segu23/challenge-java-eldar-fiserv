package com.github.segu23.ejercicioeldarfiserv.service.impl;

import com.github.segu23.ejercicioeldarfiserv.entity.CreditCard;
import com.github.segu23.ejercicioeldarfiserv.exception.CardExpiredException;
import com.github.segu23.ejercicioeldarfiserv.exception.MoneyAmountExceededException;
import com.github.segu23.ejercicioeldarfiserv.service.ICreditCardService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Override
    public void checkValidCard(CreditCard creditCard, Date transactionDate) throws CardExpiredException {
        checkCreditCardExpired(creditCard, transactionDate);
    }

    @Override
    public void checkValidTransaction(CreditCard creditCard, double moneyAmount, Date transactionDate) throws CardExpiredException, MoneyAmountExceededException {
        checkCreditCardExpired(creditCard, transactionDate);
        checkValidMoneyAmount(moneyAmount);
    }

    @Override
    public void checkCreditCardExpired(CreditCard creditCard, Date transactionDate) throws CardExpiredException {
        long transactionTimestamp = transactionDate.getTime();
        long creditCardExpirationTimestamp = creditCard.getExpiration().getTime();

        if (transactionTimestamp > creditCardExpirationTimestamp) throw new CardExpiredException();
    }

    @Override
    public void checkValidMoneyAmount(double moneyAmount) throws MoneyAmountExceededException {
        if (moneyAmount >= 1000) throw new MoneyAmountExceededException();
    }
}
