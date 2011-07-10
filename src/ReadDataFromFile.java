

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadDataFromFile {

    public ReadDataFromFile(){};

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
            List<Purchase> purchaseList = new ArrayList<Purchase>();
            File folder = new File("C:/Users/Thoughtworks/SalesTax/src/input");
            File[] inputFiles = folder.listFiles();
            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
                 List<String> purchases = readLinesFromFile(inputFiles[fileItr].getName());
                for(int lineItr = 0; lineItr < purchases.size(); lineItr++){
                    Purchase purchase = new Purchase(isImported(purchases.get(lineItr)),isTaxExempt(purchases.get(lineItr))
                            ,getNumberOfItems(purchases.get(lineItr)),getPrice(purchases.get(lineItr)));

                }

            }
        }

}
