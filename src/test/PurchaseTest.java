package test;

import main.ProcessDataFromFile;
import main.Purchase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PurchaseTest {
    private static  int itemAmount = 2;
    private static boolean imported = true;
    private static boolean taxExempt = false;
    private static double taxes = 0.0;
    private static double price = 12.49;
    private static double SALES_TAX = .1;
    private static double IMPORTED_TAX = .05;
    private static double SALES_IMPORTED_TAX = .15;
    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";


    @Test
    public void testPurchase() throws Exception{
        Purchase purchase = new Purchase(imported, taxExempt, itemAmount, price, taxes);
        assertThat(purchase.getIsImported(), is(imported));
        assertThat(purchase.getIsTaxExempt(), is(taxExempt));
        assertThat(purchase.getItemAmount(), is(itemAmount));
        assertThat(purchase.getPrice(), is(price));
    }

    @Test
    public void testShouldOnlyReturnSalesTax() throws  Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 14.99, taxes);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 18.99, taxes);
        Purchase purchase3 = new Purchase(imported, taxExempt, itemAmount, 10.00, taxes);
        purchase1.calculateTaxes(SALES_TAX);
        purchase2.calculateTaxes(SALES_TAX);
        purchase3.calculateTaxes(SALES_TAX);
        assertThat(purchase1.getTaxes(), is(1.5));
        assertThat(purchase2.getTaxes(), is(1.9));
        assertThat(purchase3.getTaxes(), is(1.0));
    }

    @Test
    public void testShouldOnlyReturnImportedTax() throws Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 11.25, taxes);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 10.00,taxes);
        purchase1.calculateTaxes(IMPORTED_TAX);
        purchase2.calculateTaxes(IMPORTED_TAX);
        assertThat(purchase1.getTaxes(), is(.6));
        assertThat(purchase2.getTaxes(), is(.5));
    }

    @Test
    public void testShouldReturnSalesAndImportedTax() throws Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 47.50,taxes);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 27.99, taxes);
        Purchase purchase3 = new Purchase(imported, taxExempt, itemAmount, 10.00, taxes);
        purchase1.calculateTaxes(SALES_IMPORTED_TAX);
        purchase2.calculateTaxes(SALES_IMPORTED_TAX);
        purchase3.calculateTaxes(SALES_IMPORTED_TAX);
        assertThat(purchase1.getTaxes(), is(7.15));
        assertThat(purchase2.getTaxes(), is(4.2));
        assertThat(purchase3.getTaxes(), is(1.50));
    }

    @Test
    public void testSetPurchaseValues() throws Exception{
        ProcessDataFromFile processDataFromFile = new ProcessDataFromFile();
        Purchase purchase = new Purchase();
        purchase.setPurchaseValues(PURCHASE1);
        assertThat(processDataFromFile.isImported(PURCHASE1), is(purchase.getIsImported()));
        assertThat(processDataFromFile.isTaxExempt(PURCHASE1), is(purchase.getIsTaxExempt()));
        assertThat(processDataFromFile.getNumberOfItems(PURCHASE1), is(purchase.getItemAmount()));
        assertThat(processDataFromFile.getPrice(PURCHASE1),is(purchase.getPrice()));
    }

    @Test
    public void testSetPurchaseTax() throws Exception{
        Purchase purchase = new Purchase();
        purchase = purchase.determinePurchaseTax(PURCHASE1);
        assertThat(purchase.getIsImported(), is(false));
        assertThat(purchase.getIsTaxExempt(), is(true));
        assertThat(purchase.getItemAmount(), is(1));
        assertThat(purchase.getPrice(), is(12.49));
        assertThat(purchase.getTaxes(), is(0.0));
    }

    @Test
    public void testTotalPriceAndTax() throws Exception{
        Purchase purchase1 = new Purchase();
        purchase1 = purchase1.determinePurchaseTax(PURCHASE1);
        Purchase purchase2 = new Purchase();
        purchase2 = purchase2.determinePurchaseTax(PURCHASE2);
        Purchase purchase3 = new Purchase();
        purchase3 = purchase3.determinePurchaseTax(PURCHASE3);
        Purchase purchase4 = new Purchase();
        purchase4 = purchase4.determinePurchaseTax(PURCHASE4);
        Purchase purchase5 = new Purchase();
        purchase5 = purchase5.determinePurchaseTax(PURCHASE5);
        Purchase purchase6 = new Purchase();
        purchase6 = purchase6.determinePurchaseTax(PURCHASE6);
        MathContext decimalPlaces = new MathContext(4);
        assertThat(purchase1.getTotalPriceAndTax(), is(new BigDecimal(12.49).round(decimalPlaces)));
        assertThat(purchase2.getTotalPriceAndTax(), is(new BigDecimal(16.49).round(decimalPlaces)));
        assertThat(purchase3.getTotalPriceAndTax(), is(new BigDecimal(.85).round(decimalPlaces)));
        assertThat(purchase4.getTotalPriceAndTax(), is(new BigDecimal(11.85).round(decimalPlaces)));
        assertThat(purchase5.getTotalPriceAndTax(), is(new BigDecimal(54.65).round(decimalPlaces)));
        assertThat(purchase6.getTotalPriceAndTax(), is(new BigDecimal(20.89).round(decimalPlaces)));
    }



}
