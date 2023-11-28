package org.practice.afternoon_classes.day_2_2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private Scanner sc;
    private final List<String> currencies = Arrays.asList("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP");
    public Utils(){
        sc = new Scanner(System.in);
    }

    public void close(){
        sc.close();
    }

    public String printAndReadOutput(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public double getDoubleInput(String message){
        String input = printAndReadOutput(message);
        if(!input.isEmpty()){
            try {
                return Double.parseDouble(input);
            }catch (NumberFormatException e){
                System.out.println("Convert input to double failed: " + e.getMessage());
            }
        }
        return 0;
    }

    public boolean validateCurrencyInput(String input){
        return currencies.contains(input);
    }

    public String getCurrencies(){
        return currencies.toString();
    }
}
