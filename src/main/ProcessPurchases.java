package main;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessPurchases {

    private static String[] inputFiles = {"src/input/input1.txt","src/input/input2.txt","src/input/input3.txt"};
    //public List<Purchase> purchases = new ArrayList<Purchase>();

    public List<String> readLinesFromFile(String filename) throws IOException {
        FileInputStream inputFile = new FileInputStream(filename);
        BufferedReader inputData = new BufferedReader(new InputStreamReader(inputFile));
        List<String> inputLines = new ArrayList<String>();
        String line;
        do {
            line = inputData.readLine();
            if(line == null)
                return inputLines;
            inputLines.add(line);
        }while(line != null);
        return inputLines;
    }


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

    public List<Purchase> generateListOfPurchases(List<String> purchasesFromFile) throws IOException {
        List<Purchase> purchases = new ArrayList<Purchase>();
            for(int lineItr = 0; lineItr < purchasesFromFile.size(); lineItr++){
                Purchase purchase = new Purchase();
                purchase.determinePurchaseTax(purchasesFromFile.get(lineItr));
                purchases.add(purchase);
            }
        return purchases;
    }

    private void createOutputFile() throws IOException {
        File outputFile = new File("src/output/output.txt");
        if(outputFile.exists())
            outputFile.delete();
        outputFile.createNewFile();
    }
}
