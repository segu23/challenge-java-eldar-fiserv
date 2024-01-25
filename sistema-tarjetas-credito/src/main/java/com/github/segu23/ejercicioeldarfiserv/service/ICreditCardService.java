package com.github.segu23.ejercicioeldarfiserv.service;

import com.github.segu23.ejercicioeldarfiserv.entity.CreditCard;
import com.github.segu23.ejercicioeldarfiserv.exception.CardExpiredException;
import com.github.segu23.ejercicioeldarfiserv.exception.MoneyAmountExceededException;

import java.util.Date;

public interface ICreditCardService {

    void checkValidCard(CreditCard creditCard, Date transactionDate) throws CardExpiredException;

    void checkValidTransaction(CreditCard creditCard, double moneyAmount, Date transactionDate) throws CardExpiredException, MoneyAmountExceededException;

    void checkCreditCardExpired(CreditCard creditCard, Date transactionDate) throws CardExpiredException;

    void checkValidMoneyAmount(double moneyAmount) throws MoneyAmountExceededException;
}
