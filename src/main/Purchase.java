package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.rmi.server.ObjID;

public class Purchase {
    private static double ROUND_INT = .5;
    private static double HUNDRED = 100.0;
    private static double ROUNDING_DECIMAL = .05;
    private static double SALES_TAX = .1;
    private static double IMPORTED_TAX = .05;
    private static double SALES_IMPORTED_TAX = .15;


    boolean isImported;
    boolean isTaxExempt;
    int itemAmount;
    double price;
    double taxes = 0.0;
    ProcessDataFromFile processDataFromFile = new ProcessDataFromFile();

    public Purchase(boolean isImported, boolean isTaxExempt, int itemAmount, double price, double taxes){
        this.isImported = isImported;
        this.isTaxExempt = isTaxExempt;
        this.itemAmount = itemAmount;
        this.price = price;
        this.taxes = taxes;
    }

    public Purchase(){
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
        MathContext decimalPlaces = new MathContext(4);
        return totalPrice.round(decimalPlaces);
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

    public Purchase determinePurchaseTax(String purchaseString) {
        setPurchaseValues(purchaseString);
        if(isImported== true && isTaxExempt==false)
            calculateTaxes(SALES_IMPORTED_TAX);
        else if (isImported== true && isTaxExempt==true)
            calculateTaxes(IMPORTED_TAX);
        else if (isImported== false && isTaxExempt==false)
            calculateTaxes(SALES_TAX);
        return new Purchase(isImported,isTaxExempt,itemAmount,price,taxes);
    }
}
