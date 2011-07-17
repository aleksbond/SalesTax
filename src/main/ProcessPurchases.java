package main;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessPurchases {

    public List<Purchase> purchases = new ArrayList<Purchase>();
    private BigDecimal totalTax = new BigDecimal(0.0);
    private BigDecimal totalCost = new BigDecimal(0.0);
    private static int decimalPlaces = 2;

    public String getFinalPurchaseWithTaxesIncluded(String purchaseLine, Purchase purchase) {
        String words[] = purchaseLine.split(" ");
        List<String> wordList = Arrays.asList(words);
        wordList.set(wordList.size() - 1, purchase.getTotalPriceAndTax().toString());
        StringBuilder buildString = new StringBuilder();
        for (String word : wordList)
        {
            buildString.append(word);
            buildString.append(" ");
        }
        return buildString.toString();
    }

    public void generateListOfPurchases(List<String> purchasesFromFile) throws IOException {
        for(int lineItr = 0; lineItr < purchasesFromFile.size(); lineItr++){
            Purchase purchase = new Purchase();
            purchase.determinePurchaseTax(purchasesFromFile.get(lineItr));
            purchases.add(purchase);
        }
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax.setScale(decimalPlaces,totalTax.ROUND_HALF_UP);
    }

    public void calculateTotalTaxes() {
        for(int itr = 0; itr < purchases.size(); itr++){
            totalTax = totalTax.add(new BigDecimal(purchases.get(itr).getTaxes()));
        }
    }

    public void calculateTotalCost() {
        for(int itr = 0; itr < purchases.size(); itr++){
            totalCost = totalCost.add(purchases.get(itr).getTotalPriceAndTax());
        }
    }
}
