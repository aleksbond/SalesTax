package main;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessDataFromFile {

    public ProcessDataFromFile(){};

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
}


