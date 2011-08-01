package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteToFile {

    private BasketFactory basketFactory;

    public ReadAndWriteToFile(BasketFactory basketFactory){
        this.basketFactory = basketFactory;
    }


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
                List<String> purchases = readLinesFromFile(inputFiles[fileItr]);
                Basket basket = basketFactory.getProcessPurchases();
                basket.writePurchasesToFile(output, purchases);
            }
        output.close();
    }
}
