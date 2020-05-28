package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorController {

    public void calculate() {
        System.out.println("Dany kalkulator walutowy pozwoli Ci przeliczyć kwotę w EUR na konkretną walutę");
        System.out.println("");
        boolean result;
        do {
            result = calculateLoop();
        } while (result);
    }

    private boolean calculateLoop() {
        Scanner scanner = new Scanner(System.in);
        CalculatorService calculatorService = new CalculatorService();
        DataParser dataParser = new DataParser();

        System.out.print("Lista dostępnych walut docelowych: ");
        dataParser.parseData().forEach(cube -> System.out.print(cube.getCurrency() + " "));
        System.out.println("");
        System.out.print("Z powyższych walut wpisz docelową walutę do przeliczenia z EUR: ");

        Boolean isCurrencyNameCorrect = false;
        String currencyName = null;
        do {
            currencyName = scanner.nextLine();
            isCurrencyNameCorrect = calculatorService.validationOfCurrencyName(currencyName);
            if(!isCurrencyNameCorrect){
                System.out.print("Wpisano niepoprawną nazwę waluty. Wpisz jeszcze raz poprawną nazwę waluty: ");
            }
        } while(!isCurrencyNameCorrect);

        Boolean isAmountCorrect = false;
        String amount = null;
        do{
            System.out.print("Wpisz kwotę jaką chcesz przeliczyć(EUR): ");
            amount = scanner.nextLine();
            isAmountCorrect = calculatorService.validationOfAmount(amount);
        }while (!isAmountCorrect);
        Double finalAmount = Double.valueOf(amount);
        Double resultOfCalculateAmount = calculatorService.calculateAmount(currencyName, finalAmount);

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Wynik:  " + df.format(finalAmount) + " EUR" + " = " + df.format(resultOfCalculateAmount) + " " + currencyName.toUpperCase());
        System.out.println("Czy chcesz dalej korzystać z kalkulatora? T/N");
        String answer = scanner.nextLine();
        boolean decision = false;
        if (answer.equals("t") || answer.equals("T")) {
            decision = true;
        }
        return decision;
    }
}
