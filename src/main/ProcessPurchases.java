package main;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessPurchases {

    public List<Purchase> purchases = new ArrayList<Purchase>();
    private double totalSalesTax;
    private double totalCost;

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

    public void createOutputFile() throws IOException {
        File outputFile = new File("src/output/output.txt");
        if(outputFile.exists())
            outputFile.delete();
        outputFile.createNewFile();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalSalesTax() {
        return totalSalesTax;
    }
}
