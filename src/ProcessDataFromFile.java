

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

            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
                File outputFile = new File("output"+(fileItr + 1)+".txt");
                outputFile.createNewFile();
                Writer output = null;
                output = new BufferedWriter(new FileWriter(outputFile));
                List<Purchase> purchaseList = new ArrayList<Purchase>();
                List<String> purchases = readLinesFromFile(inputFiles[fileItr]);
                double totalSalesTax = 0.0;
                double total = 0.0;
                for(int lineItr = 0; lineItr < purchases.size()-1; lineItr++){
                    Purchase purchase = processPurchases(purchases.get(lineItr));
                    purchaseList.add(purchase);
                    totalSalesTax += purchase.getTotalTax();
                    total += purchase.getTotalPriceAndTax();

                    writeFinalPurchaseToOutput(output, purchases, lineItr, purchase);

                }
                output.write("Sales Taxes: "+ roundDoubleToTwoDecimalPlaces(totalSalesTax) + "\n");
                output.write("Total: "+roundDoubleToTwoDecimalPlaces(total));
                output.close();
            }
        }

    private void writeFinalPurchaseToOutput(Writer output, List<String> purchases, int lineItr, Purchase purchase) throws IOException {
        String words[] = purchases.get(lineItr).split(" ");
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

    public double roundDoubleToTwoDecimalPlaces(double dbl){
        int estimate = (int)(dbl*100.0);
        double roundedDbl = ((double)estimate)/100.0;
        return roundedDbl;
    }
}


