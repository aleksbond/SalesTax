package main;

import java.math.BigDecimal;

public class Item {
    private static double ROUND_INT = .5;
    private static double HUNDRED = 100.0;
    private static double ROUNDING_DECIMAL = .05;
    private static double SALES_TAX = .1;
    private static double IMPORT_DUTY = .05;
    private static double SALES_IMPORT_TAX = .15;
    private static int decimalPlaces = 2;


    boolean isImported;
    boolean isTaxExempt;
    int itemAmount;
    double price;
    double taxes = 0.0;
    ProcessDataFromFile processDataFromFile = new ProcessDataFromFile();

    public Item(boolean isImported, boolean isTaxExempt, int itemAmount, double price, double taxes){
        this.isImported = isImported;
        this.isTaxExempt = isTaxExempt;
        this.itemAmount = itemAmount;
        this.price = price;
        this.taxes = taxes;
    }

    public Item(){
    }

    public boolean getIsImported(){
        return isImported;
    }

    public boolean getIsTaxExempt(){
        return isTaxExempt;
    }

    public int getItemAmount(){
        return itemAmount;
    }

    public double getPrice(){
        return price;
    }

    public double getTaxes() {
        return roundDoubleToTwoDecimalPlaces(taxes);
    }

    public BigDecimal getTotalPriceAndTax(){
        BigDecimal priceBd, taxesBd;
        priceBd = new BigDecimal(price);
        taxesBd = new BigDecimal(taxes);
        BigDecimal totalPrice = priceBd.add(taxesBd);
        return totalPrice.setScale(decimalPlaces,totalPrice.ROUND_HALF_UP);
    }

    public void calculateTaxes(double taxPercentage) {
        int priceInPennies = (int)(price*HUNDRED);
        int taxesInPennies = (int)(priceInPennies*taxPercentage + ROUND_INT);
        double taxes = ((double)taxesInPennies)/HUNDRED;
        double roundedTaxes = Math.ceil(taxes/ROUNDING_DECIMAL)*ROUNDING_DECIMAL;
        this.taxes = roundedTaxes;
    }

    public double roundDoubleToTwoDecimalPlaces(double dbl){
        int estimate = (int)(dbl*100.0);
        double roundedDbl = ((double)estimate)/100.0;
        return roundedDbl;
    }

    public void setPurchaseValues(String purchaseString){
       isImported = processDataFromFile.isImported(purchaseString);
       isTaxExempt = processDataFromFile.isTaxExempt(purchaseString);
       itemAmount = processDataFromFile.getNumberOfItems(purchaseString);
       price = processDataFromFile.getPrice(purchaseString);
    }

    public Item determinePurchaseTax(String purchaseString) {
        setPurchaseValues(purchaseString);
        if(isImported== true && isTaxExempt==false)
            calculateTaxes(SALES_IMPORT_TAX);
        else if (isImported== true && isTaxExempt==true)
            calculateTaxes(IMPORT_DUTY);
        else if (isImported== false && isTaxExempt==false)
            calculateTaxes(SALES_TAX);
        return new Item(isImported,isTaxExempt,itemAmount,price,taxes);
    }
}
