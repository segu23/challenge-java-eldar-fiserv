package com.github.segu23.ejercicioeldarfiserv;

import com.github.segu23.ejercicioeldarfiserv.constant.CardBrand;
import com.github.segu23.ejercicioeldarfiserv.entity.CreditCard;
import com.github.segu23.ejercicioeldarfiserv.entity.User;
import com.github.segu23.ejercicioeldarfiserv.exception.CardExpiredException;
import com.github.segu23.ejercicioeldarfiserv.exception.CardNotFoundException;
import com.github.segu23.ejercicioeldarfiserv.exception.InvalidCardBrandException;
import com.github.segu23.ejercicioeldarfiserv.exception.MoneyAmountExceededException;
import com.github.segu23.ejercicioeldarfiserv.service.ICreditCardService;
import com.github.segu23.ejercicioeldarfiserv.service.IFeeService;
import com.github.segu23.ejercicioeldarfiserv.service.impl.CreditCardServiceImpl;
import com.github.segu23.ejercicioeldarfiserv.service.impl.FeeServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class CliExampleApplication {

    // messages
    private static final String VALID_OPERATION_MESSAGE = "La operación es válida.";
    private static final String VALID_CARD_MESSAGE = "La tarjeta es válida para operar.";
    private static final String INVALID_OPERATION_CARD_EXPIRED_MESSAGE = "La operación es inválida, la tarjeta se encuentra expirada.";
    private static final String INVALID_OPERATION_MONEY_LIMIT_EXCEEDED_MESSAGE = "La operación es inválida, el monto excede el límite.";
    private static final String INVALID_CARD_BRAND_AND_CORRECT_BRANDS_MESSAGE = "La marca ingresada es inválida. Opciones válidas: ";
    private static final String FEE_AND_TOTAL_COST_MESSAGE = "La tasa en este momento es de %.2f%%. Coste de la operación: %.2f";
    private static final String CARD_NOT_FOUND_MESSAGE = "La tarjeta ingresada no fue encontrada.";
    private static final String CARD_BRAND_INPUT_MESSAGE = " > Ingrese la marca de la tarjeta: ";
    private static final String CARD_NUMBER_INPUT_MESSAGE = " > Ingrese el número de la tarjeta: ";
    private static final String CARD_NUMBER_ON_POSITION_INPUT_MESSAGE = " > Ingrese el número de la %s tarjeta: ";
    private static final String MONEY_AMOUNT_INPUT_MESSAGE = " > Ingrese el importe de la operación: ";
    private static final String NOT_EQUAL_CARDS_MESSAGE = "Las tarjetas ingresadas no son iguales";
    private static final String EQUAL_CARDS_MESSAGE = "Ambas tarjetas son iguales.";
    private static final String INVALID_MENU_OPTION_MESSAGE = "La opción ingresada es inválida.";
    private static final String CLOSING_APPLICATION_MESSAGE = "Cerrando aplicación...";
    private static final String INVALID_INT_INPUT_MESSAGE = "Error. Debe ingresar un numero entero.";
    private static final String INVALID_DOUBLE_INPUT_MESSAGE = "Error. Debe ingresar un numero decimal.";
    private static final String MENU_OPTIONS = """
            Ingrese una de las siguientes opciones:
              1 - Ver información de una tarjeta.
              2 - Verificar operación válida.
              3 - Verificar tarjeta válida.
              4 - Verificar tarjetas iguales.
              5 - Obtener tasa de servicio.
              6 - Cerrar la aplicación.
            """;
    // services
    private final IFeeService feeService = new FeeServiceImpl();
    private final ICreditCardService creditCardService = new CreditCardServiceImpl();
    // cards
    private final CreditCard[] creditCards = new CreditCard[]{
            new CreditCard(1, 1111222233334444L, new User(3, "John", "Doe"), new Date(), CardBrand.VISA),
            new CreditCard(2, 2222333344445555L, new User(2, "Pedro", "Fernandez"), new Date(), CardBrand.NARA),
            new CreditCard(3, 3333444455556666L, new User(1, "Juan", "Perez"), new Date(), CardBrand.AMEX)
    };

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new CliExampleApplication().application(scanner);
        }
    }

    public void application(Scanner scanner) {
        while (true) {
            printMenu();

            if (scanner.hasNextLong()) {
                int option = scanner.nextInt();

                switch (option) {
                    case 1 -> {
                        getCardInfo(scanner);
                    }
                    case 2 -> {
                        checkValidTransaction(scanner);
                    }
                    case 3 -> {
                        checkValidCard(scanner);
                    }
                    case 4 -> {
                        checkEqualCards(scanner);
                    }
                    case 5 -> {
                        getTransactionFee(scanner);
                    }
                    case 6 -> {
                        System.out.println(CLOSING_APPLICATION_MESSAGE);
                        return;
                    }
                    default -> {
                        System.out.println(INVALID_MENU_OPTION_MESSAGE);
                    }
                }
            } else {
                scanner.nextLine();
                System.out.println(INVALID_INT_INPUT_MESSAGE);
            }
        }
    }

    private void getCardInfo(Scanner scanner) {
        System.out.println(CARD_NUMBER_INPUT_MESSAGE);

        if (scanner.hasNextLong()) {
            long cardNumber = scanner.nextLong();

            try {
                CreditCard creditCard = findCardByNumber(cardNumber);
                System.out.println(creditCard.toString());
            } catch (CardNotFoundException e) {
                System.out.println(CARD_NOT_FOUND_MESSAGE);
            }
        } else {
            scanner.nextLine();
            System.out.println(INVALID_INT_INPUT_MESSAGE);
        }
    }

    private void checkValidTransaction(Scanner scanner) {
        System.out.println(CARD_NUMBER_INPUT_MESSAGE);

        if (scanner.hasNextLong()) {
            long cardNumber = scanner.nextLong();

            System.out.println(MONEY_AMOUNT_INPUT_MESSAGE);

            if (scanner.hasNextDouble()) {
                double moneyAmount = scanner.nextDouble();

                try {
                    CreditCard creditCard = findCardByNumber(cardNumber);

                    try {
                        creditCardService.checkValidTransaction(creditCard, moneyAmount, new Date());
                        System.out.println(VALID_OPERATION_MESSAGE);
                    } catch (CardExpiredException e) {
                        System.out.println(INVALID_OPERATION_CARD_EXPIRED_MESSAGE);
                    } catch (MoneyAmountExceededException e) {
                        System.out.println(INVALID_OPERATION_MONEY_LIMIT_EXCEEDED_MESSAGE);
                    }
                } catch (CardNotFoundException e) {
                    System.out.println(CARD_NOT_FOUND_MESSAGE);
                }
            } else {
                scanner.nextLine();
                System.out.println(INVALID_DOUBLE_INPUT_MESSAGE);
            }
        } else {
            scanner.nextLine();
            System.out.println(INVALID_INT_INPUT_MESSAGE);
        }
    }

    private void checkValidCard(Scanner scanner) {
        System.out.println(CARD_NUMBER_INPUT_MESSAGE);

        if (scanner.hasNextLong()) {
            long cardNumber = scanner.nextLong();

            try {
                CreditCard creditCard = findCardByNumber(cardNumber);

                try {
                    creditCardService.checkValidCard(creditCard, new Date());
                    System.out.println(VALID_CARD_MESSAGE);
                } catch (CardExpiredException e) {
                    System.out.println(INVALID_OPERATION_CARD_EXPIRED_MESSAGE);
                }
            } catch (CardNotFoundException e) {
                System.out.println(CARD_NOT_FOUND_MESSAGE);
            }
        } else {
            scanner.nextLine();
            System.out.println(INVALID_INT_INPUT_MESSAGE);
        }
    }

    private void checkEqualCards(Scanner scanner) {
        System.out.println(String.format(CARD_NUMBER_ON_POSITION_INPUT_MESSAGE, "primer"));

        if (scanner.hasNextLong()) {
            long firstCardNumber = scanner.nextLong();

            try {
                CreditCard firstCreditCard = findCardByNumber(firstCardNumber);
                System.out.println(String.format(CARD_NUMBER_ON_POSITION_INPUT_MESSAGE, "segunda"));

                if (scanner.hasNextLong()) {
                    long secondCardNumber = scanner.nextLong();

                    try {
                        CreditCard secondCreditCard = findCardByNumber(secondCardNumber);

                        if (firstCreditCard.equals(secondCreditCard)) {
                            System.out.println(EQUAL_CARDS_MESSAGE);
                        } else {
                            System.out.println(NOT_EQUAL_CARDS_MESSAGE);
                        }
                    } catch (CardNotFoundException e) {
                        System.out.println(CARD_NOT_FOUND_MESSAGE);
                    }
                } else {
                    scanner.nextLine();
                    System.out.println(INVALID_INT_INPUT_MESSAGE);
                }
            } catch (CardNotFoundException e) {
                System.out.println(CARD_NOT_FOUND_MESSAGE);
            }
        } else {
            scanner.nextLine();
            System.out.println(INVALID_INT_INPUT_MESSAGE);
        }
    }

    private void getTransactionFee(Scanner scanner) {
        System.out.println(MONEY_AMOUNT_INPUT_MESSAGE);
        if (scanner.hasNextDouble()) {
            double moneyAmount = scanner.nextDouble();

            System.out.println(CARD_BRAND_INPUT_MESSAGE);
            String cardBrandName = scanner.next().toUpperCase(Locale.ROOT);

            try {
                CardBrand cardBrand = CardBrand.valueOf(cardBrandName);

                double fee = feeService.calcCurrentServiceFee(cardBrand);
                double moneyAmountWithFee = feeService.getMoneyAmountWithFee(cardBrand, moneyAmount);

                System.out.println(String.format(FEE_AND_TOTAL_COST_MESSAGE, fee, moneyAmountWithFee));
            } catch (IllegalArgumentException | InvalidCardBrandException e) {
                System.out.println(String.format(INVALID_CARD_BRAND_AND_CORRECT_BRANDS_MESSAGE, Arrays.toString(CardBrand.values())));
            }
        } else {
            scanner.nextLine();
            System.out.println(INVALID_DOUBLE_INPUT_MESSAGE);
        }
    }

    private void printMenu() {
        System.out.println(MENU_OPTIONS);
    }

    public CreditCard findCardByNumber(long cardNumber) throws CardNotFoundException {
        return Arrays.stream(creditCards).filter(creditCard -> creditCard.getCardNumber() == cardNumber).findFirst().orElseThrow(CardNotFoundException::new);
    }
}