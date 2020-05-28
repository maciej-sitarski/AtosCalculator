package org.example;

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
            double doubleValue = Double.parseDouble(nullCheckedValue);
            if(doubleValue<0.0){
                throw new NumberFormatException();
            }
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Wpisano niepoprawną kwotę, wpisz ponownie poprawną kwotę.");
            return false;
        }
    }

    public Double calculateAmount(String currencyName, Double amount) {
        DataParser dataParser = new DataParser();

        String currencyNameUpperCase = Optional.ofNullable(currencyName)
                .map(String::toUpperCase)
                .orElse(null);

        String searchRate = dataParser.parseData().stream()
                .filter(cube -> cube.getCurrency().equals(currencyNameUpperCase))
                .findFirst()
                .map(Cube::getRate)
                .orElse("0");

        Double checkedAmount = Optional.ofNullable(amount).orElse(0.0);
        Double rate = Double.valueOf(searchRate);
        Double resultOfCalculation = rate * checkedAmount;

        return resultOfCalculation;
    }
}
