

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessDataFromFile extends CalculateTaxes{

    private static String[] inputFiles = {"/Users/Thoughtworks/SalesTax/src/input1.txt","/Users/Thoughtworks/SalesTax/src/input2.txt"
            ,"/Users/Thoughtworks/SalesTax/src/input3.txt"};

    public ProcessDataFromFile(){};

    public List<String> readLinesFromFile(String filename) throws IOException {
        FileInputStream inputFile = new FileInputStream(filename);
        BufferedReader inputData = new BufferedReader(new InputStreamReader(inputFile));

        List<String> inputLines = new ArrayList<String>();
        String line;

        do {
            line = inputData.readLine();
            inputLines.add(line);
        }while(line != null);

        return inputLines;
    }

    public int getNumberOfItems(String purchase){
        Matcher matcher = Pattern.compile("\\d+").matcher(purchase);
        matcher.find();
        int itemAmount = Integer.valueOf(matcher.group());
        return itemAmount;
    }

    public boolean isTaxExempt(String purchase) {
        if(purchase.contains("book")||purchase.contains("pills")||purchase.contains("chocolate")||purchase.contains("chocolates"))
            return true;
        else
            return false;
    }

    public boolean isImported(String purchase) {
        return purchase.contains("imported");
    }

    public double getPrice(String purchase) {
        String words[] = purchase.split(" ");
        List<String> wordList = Arrays.asList(words);
        Collections.reverse(wordList);
        return Double.parseDouble(wordList.get(0));
    }

    public void loopThroughFiles() throws IOException {
        File outputFile = new File("output.txt");
        outputFile.createNewFile();
        Writer output = null;
        output = new BufferedWriter(new FileWriter(outputFile));
            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
                List<String> purchases = readLinesFromFile(inputFiles[fileItr]);
                int totalSalesTax = 0;
                int total = 0;
                for(int lineItr = 0; lineItr < purchases.size()-1; lineItr++){
                    Purchase purchase = processPurchases(purchases.get(lineItr));

                    totalSalesTax += (int)(purchase.getTotalTax()*100);
                    total += (int)(purchase.getTotalPriceAndTax()*100);

                    writeFinalPurchaseToOutput(output, purchases.get(lineItr), purchase);
                }
                output.write("Sales Taxes: "+ (((double)totalSalesTax)/100.0) + "\n");
                output.write("Total: "+(((double)total)/100.0)+"\n");
                output.write("\n");
            }
        output.close();
    }

    private void writeFinalPurchaseToOutput(Writer output, String purchaseLine, Purchase purchase) throws IOException {
        String words[] = purchaseLine.split(" ");
        List<String> wordList = Arrays.asList(words);
        wordList.set(wordList.size()-1, Double.toString(purchase.getTotalPriceAndTax()));
        StringBuilder buildString = new StringBuilder();
        for (String word : wordList)
        {
            buildString.append(word);
            buildString.append("\t");
        }
        output.write(buildString.toString()+"\n");
    }

    private Purchase processPurchases(String purchaseOne) {
        Purchase purchase = new Purchase(isImported(purchaseOne),isTaxExempt(purchaseOne)
                ,getNumberOfItems(purchaseOne),getPrice(purchaseOne));
        if(purchase.getIsImported()== true && purchase.getIsTaxExempt()==false){
            purchase.setSalesAndImportedTax(calculateSalesAndImportedTax(purchase.getPrice()));
        }
        else if (purchase.getIsImported()== true && purchase.getIsTaxExempt()==true){
            purchase.setImportedTax(calculateOnlyImportedTax(purchase.getPrice()));
        }
        else if (purchase.getIsImported()== false && purchase.getIsTaxExempt()==false){
            purchase.setSalesTax(calculateOnlySalesTax(purchase.getPrice()));
        }
        return purchase;
    }
}


