package main;

import java.io.IOException;

public class SalesTaxApp {

    public static void main(String[] args){
        BasketFactory basketFactory;
        ReadAndWriteToFile readAndWriteToFile;
        basketFactory = new BasketFactory();
        readAndWriteToFile = new ReadAndWriteToFile(basketFactory);
        try {
            readAndWriteToFile.writeToOutputFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
