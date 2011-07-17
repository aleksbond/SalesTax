package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessPurchases {

    private static String[] inputFiles = {"src/input/input1.txt","src/input/input2.txt"
            ,"src/input/input3.txt"};
    public List<Purchase> purchases = new ArrayList<Purchase>();

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
}
