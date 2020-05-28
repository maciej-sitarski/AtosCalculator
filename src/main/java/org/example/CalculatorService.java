package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculatorService {

    public Boolean validationOfCurrencyName(String currencyNameUser) {
        DataParser dataParser = new DataParser();

        List<String> listOfCurrencyName = dataParser.parseData().stream()
                .map(cube -> cube.getCurrency().toLowerCase())
                .collect(Collectors.toList());

        String currencyNameUserLowerCase = Optional.ofNullable(currencyNameUser)
                .map(String::toLowerCase)
                .orElse(null);

        Boolean isNameCorrect = listOfCurrencyName.stream()
                .anyMatch(currencyName -> currencyName.equals(currencyNameUserLowerCase));
        return isNameCorrect;
    }

    public Boolean validationOfAmount(String value) {

        String nullCheckedValue = Optional.ofNullable(value).orElse("");

        try {
            BigDecimal doubleValue = new BigDecimal(nullCheckedValue);
            if (doubleValue.compareTo(BigDecimal.ZERO) < 0) {
                throw new NumberFormatException();
            }
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Wpisano niepoprawną kwotę, wpisz ponownie poprawną kwotę.");
            return false;
        }
    }

    public BigDecimal calculateAmount(String currencyName, BigDecimal amount) {
        DataParser dataParser = new DataParser();

        String currencyNameUpperCase = Optional.ofNullable(currencyName)
                .map(String::toUpperCase)
                .orElse(null);

        BigDecimal rate = dataParser.parseData().stream()
                .filter(cube -> cube.getCurrency().equals(currencyNameUpperCase))
                .findFirst()
                .map(Cube::getRate)
                .orElse(BigDecimal.ZERO);

        BigDecimal checkedAmount = Optional.ofNullable(amount).orElse(BigDecimal.ZERO);
        BigDecimal resultOfCalculation = rate.multiply(checkedAmount);

        return resultOfCalculation;
    }
}
