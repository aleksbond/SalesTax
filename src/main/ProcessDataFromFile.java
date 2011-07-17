package main;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessDataFromFile {

    private static String[] inputFiles = {"src/input/input1.txt","src/input/input2.txt"
            ,"src/input/input3.txt"};

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

//    public void loopThroughFiles() throws IOException {
//        File outputFile = new File("src/output/output.txt");
//        if(outputFile.exists())
//            outputFile.delete();
//        outputFile.createNewFile();
//        Writer output = null;
//        output = new BufferedWriter(new FileWriter(outputFile));
//            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
//                List<String> purchases = readLinesFromFile(inputFiles[fileItr]);
//                int totalSalesTax = 0;
//                int total = 0;
//                for(int lineItr = 0; lineItr < purchases.size()-1; lineItr++){
//                    Purchase purchase = processPurchases(purchases.get(lineItr));
//
//                    totalSalesTax += (int)(purchase.getTaxes()*100);
//                    total += (int)(purchase.getTotalPriceAndTax()*100);
//
//                    writeFinalPurchaseToOutput(output, purchases.get(lineItr), purchase);
//                }
//                output.write("Sales Taxes: "+ (((double)totalSalesTax)/100.0) + "\n");
//                output.write("Total: "+(((double)total)/100.0)+"\n");
//                output.write("\n");
//            }
//        output.close();
//    }
}


