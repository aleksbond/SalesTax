package main;

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

    public void createOutputFile() throws IOException {
        File outputFile = new File("src/output/output.txt");
        if(outputFile.exists())
            outputFile.delete();
        outputFile.createNewFile();
    }
}
