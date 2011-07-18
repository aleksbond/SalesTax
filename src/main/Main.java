package main;

import java.io.IOException;

public class Main {
    ProcessPurchasesFactory processPurchasesFactory;
    ReadAndWriteToFile readAndWriteToFile;
    public Main() throws IOException {
        processPurchasesFactory = new ProcessPurchasesFactory();
        readAndWriteToFile = new ReadAndWriteToFile(processPurchasesFactory);
        readAndWriteToFile.writeToOutputFile();
    }
}
