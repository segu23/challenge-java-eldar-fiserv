package com.github.segu23.ejercicioeldarfiserv.controller;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;
import com.github.segu23.ejercicioeldarfiserv.exception.InvalidCardBrandException;
import com.github.segu23.ejercicioeldarfiserv.model.TransactionFeeRequest;
import com.github.segu23.ejercicioeldarfiserv.model.TransactionFeeResponse;
import com.github.segu23.ejercicioeldarfiserv.service.IFeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    private IFeeService feeService;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @PostMapping("/transaction-fee")
    public ResponseEntity<TransactionFeeResponse> calculateTransactionFee(@Valid @RequestBody TransactionFeeRequest transactionFeeRequest) throws InvalidCardBrandException {
        try {
            CardBrand cardBrand = CardBrand.valueOf(transactionFeeRequest.getCardBrandName());
            TransactionFeeResponse transactionFeeResponse = new TransactionFeeResponse();

            double moneyAmountWithFees = feeService.getMoneyAmountWithFee(cardBrand, transactionFeeRequest.getMoneyAmount());
            String moneyAmountWithFeesFormatted = df.format(moneyAmountWithFees).replace(",", ".");
            moneyAmountWithFees = Double.parseDouble(moneyAmountWithFeesFormatted);
            String feePercentage = String.format("%.2f%%", feeService.calcCurrentServiceFee(cardBrand));

            transactionFeeResponse.setActualFeePercentage(feePercentage);
            transactionFeeResponse.setMoneyAmountWithFees(moneyAmountWithFees);
            transactionFeeResponse.setMoneyAmount(transactionFeeRequest.getMoneyAmount());
            transactionFeeResponse.setCardBrand(cardBrand);

            return ResponseEntity.ok(transactionFeeResponse);
        } catch (IllegalArgumentException e) {
            throw new InvalidCardBrandException();
        }
    }
}
