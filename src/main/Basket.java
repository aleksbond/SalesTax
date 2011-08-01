package main;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basket {

    public List<Item> items = new ArrayList<Item>();
    public List<String> finalPurchases = new ArrayList<String>();
    private BigDecimal totalTax = new BigDecimal(0.0);
    private BigDecimal totalCost = new BigDecimal(0.0);
    private static int decimalPlaces = 2;

    public String getFinalPurchaseWithTaxesIncluded(String purchaseLine, Item item) {
        String words[] = purchaseLine.split(" ");
        List<String> wordList = Arrays.asList(words);
        wordList.set(wordList.size() - 1, item.getTotalPriceAndTax().toString());
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
            Item item = new Item();
            item.determinePurchaseTax(purchasesFromFile.get(lineItr));
            items.add(item);
            finalPurchases.add(getFinalPurchaseWithTaxesIncluded(purchasesFromFile.get(lineItr), item));
        }
    }

    public BigDecimal getTotalCost() {
        return totalCost.setScale(decimalPlaces,totalCost.ROUND_HALF_UP);
    }

    public BigDecimal getTotalTax() {
        return totalTax.setScale(decimalPlaces,totalTax.ROUND_HALF_UP);
    }

    public void calculateTotalTaxes() {
        for(int itr = 0; itr < items.size(); itr++){
            totalTax = totalTax.add(new BigDecimal(items.get(itr).getTaxes()));
        }
    }

    public void calculateTotalCost() {
        for(int itr = 0; itr < items.size(); itr++){
            totalCost = totalCost.add(items.get(itr).getTotalPriceAndTax());
        }
    }

    public void writePurchasesToFile(Writer output, List<String> purchases) throws IOException {
        generateListOfPurchases(purchases);
        for(int lineItr = 0; lineItr < purchases.size(); lineItr++){
            output.write(finalPurchases.get(lineItr)+"\n");
        }
        calculateTotalTaxes();
        calculateTotalCost();
        output.write("Sales Taxes: "+ getTotalTax()+"\n");
        output.write("Total: "+getTotalCost()+"\n\n");
    }
}
