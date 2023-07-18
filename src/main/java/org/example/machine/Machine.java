package org.example.machine;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Machine {
    private List<String> validCoins = Arrays.asList("quarter", "dime", "nickel");
    private Map<String, Integer> coinValues = new HashMap<String, Integer>() {{
        put("quarter", 25);
        put("dime", 10);
        put("nickel", 5);
    }};
    private Map<String, Integer> productPrices = new HashMap<String, Integer>() {{
        put("cola", 100);
        put("chips", 50);
        put("candy", 65);
    }};
    private int currentAmount = 0;
    private int change = 0;
    private String display = "INSERT COIN";

    public boolean insertCoin(String coin) {
        if (validCoins.contains(coin)) {
            currentAmount += coinValues.get(coin);
            updateDisplay();
            return true;
        } else {
            return false;
        }
    }

    public String selectProduct(String product) {
        if (productPrices.containsKey(product)) {
            int price = productPrices.get(product);
            if (currentAmount >= price) {
                currentAmount -= price;
                change = currentAmount;
                currentAmount = 0;
                updateDisplay();
                return "THANK YOU";
            } else {
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat df = (DecimalFormat) nf;
                df.applyPattern("0.00");
                display = "PRICE: " + df.format(price / 100.0);
                return display;
            }
        } else {
            return "INVALID PRODUCT";
        }
    }

    public int getChange() {
        int currentChange = change;
        change = 0;
        return currentChange;
    }

    public int returnCoins() {
        int coinsToReturn = currentAmount;
        currentAmount = 0;
        updateDisplay();
        return coinsToReturn;
    }

    public String checkDisplay() {
        String currentDisplay = display;
        if (display.startsWith("PRICE:")) {
            updateDisplay();
        }
        return currentDisplay;
    }

    private void updateDisplay() {
        if (currentAmount == 0) {
            display = "INSERT COIN";
        } else {
            display = String.format("%.2f", currentAmount / 100.0);
        }
    }
}
