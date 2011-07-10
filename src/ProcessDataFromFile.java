

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessDataFromFile extends CalculateTaxes{

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
        if(purchase.contains("imported"))
            return true;
        else
            return false;
    }

    public double getPrice(String purchase) {
        String words[] = purchase.split(" ");
        List<String> wordList = Arrays.asList(words);
        Collections.reverse(wordList);
        return Double.parseDouble(wordList.get(0));
    }

    public void loopThroughFiles() throws IOException {
            File folder = new File("C:/Users/Thoughtworks/SalesTax/src/input");
            File[] inputFiles = folder.listFiles();
            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
                List<Purchase> purchaseList = new ArrayList<Purchase>();
                List<String> purchases = readLinesFromFile(inputFiles[fileItr].getName());
                for(int lineItr = 0; lineItr < purchases.size(); lineItr++){
                    Purchase purchase = processPurchases(purchases, lineItr);
                    purchaseList.add(purchase);

                    String words[] = purchases.get(lineItr).split(" ");
                    List<String> wordList = Arrays.asList(words);
                    wordList.set(wordList.size()-1, Double.toString(purchase.getTotalPriceAndTax()));
                    StringBuilder buildString = new StringBuilder();
                    for (String word : wordList)
                    {
                        buildString.append(word);
                        buildString.append("\t");
                    }

                    File output = new File("output/output"+(lineItr + 1)+".txt");
                    output.createNewFile();

                }

            }
        }

    private Purchase processPurchases(List<String> purchases, int lineItr) {
        Purchase purchase = new Purchase(isImported(purchases.get(lineItr)),isTaxExempt(purchases.get(lineItr))
                ,getNumberOfItems(purchases.get(lineItr)),getPrice(purchases.get(lineItr)));
        if(purchase.getIsImported()== true && purchase.getIsTaxExempt()==false){
            purchase.setImportedTax(calculateOnlyImportedTax(purchase.getPrice()));
            purchase.setSalesTax(calculateOnlySalesTax(purchase.getPrice()));
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
