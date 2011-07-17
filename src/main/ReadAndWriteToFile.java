package main;

import com.sun.org.apache.xml.internal.serializer.OutputPropertyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteToFile {
    private static String[] inputFiles = {"src/input/input1.txt","src/input/input2.txt","src/input/input3.txt"};

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

    public File createOutputFile() throws IOException {
        File outputFile = new File("src/output/output.txt");
        if(outputFile.exists())
            outputFile.delete();
        outputFile.createNewFile();
        return outputFile;
    }

    public void writeToOutputFile() throws IOException {
        File outputFile = createOutputFile();
        Writer output = new BufferedWriter(new FileWriter(outputFile));
            for(int fileItr = 0; fileItr < inputFiles.length; fileItr++){
                ProcessPurchases processPurchases = new ProcessPurchases();
                List<String> purchases = readLinesFromFile(inputFiles[fileItr]);
                processPurchases.generateListOfPurchases(purchases);
                for(int lineItr = 0; lineItr < purchases.size(); lineItr++){
                    output.write(processPurchases.finalPurchases.get(lineItr)+"\n");
                }
                processPurchases.calculateTotalTaxes();
                processPurchases.calculateTotalCost();
                output.write("Sales Taxes: "+ processPurchases.getTotalTax()+"\n");
                output.write("Total: "+processPurchases.getTotalCost()+"\n\n");
            }
        output.close();
    }
}
