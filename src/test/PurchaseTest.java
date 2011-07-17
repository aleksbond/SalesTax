package test;

import main.ProcessDataFromFile;
import main.Purchase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PurchaseTest {
    private static  int itemAmount = 2;
    private static boolean imported = true;
    private static boolean taxExempt = false;
    private static double price = 12.49;
    private static double SALES_TAX = .1;
    private static double IMPORTED_TAX = .05;
    private static double SALES_IMPORTED_TAX = .15;
    private static String PURCHASE1 = "1 book at 12.49";

    @Test
    public void testPurchase() throws Exception{
        Purchase purchase = new Purchase(imported, taxExempt, itemAmount, price);
        assertThat(purchase.getIsImported(), is(imported));
        assertThat(purchase.getIsTaxExempt(), is(taxExempt));
        assertThat(purchase.getItemAmount(), is(itemAmount));
        assertThat(purchase.getPrice(), is(price));
    }

    @Test
    public void testShouldOnlyReturnSalesTax() throws  Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 14.99);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 18.99);
        Purchase purchase3 = new Purchase(imported, taxExempt, itemAmount, 10.00);
        purchase1.calculateTaxes(SALES_TAX);
        purchase2.calculateTaxes(SALES_TAX);
        purchase3.calculateTaxes(SALES_TAX);
        assertThat(purchase1.getTaxes(), is(1.5));
        assertThat(purchase2.getTaxes(), is(1.9));
        assertThat(purchase3.getTaxes(), is(1.0));
    }

    @Test
    public void testShouldOnlyReturnImportedTax() throws Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 11.25);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 10.00);
        purchase1.calculateTaxes(IMPORTED_TAX);
        purchase2.calculateTaxes(IMPORTED_TAX);
        assertThat(purchase1.getTaxes(), is(.6));
        assertThat(purchase2.getTaxes(), is(.5));
    }

    @Test
    public void testShouldReturnSalesAndImportedTax() throws Exception{
        Purchase purchase1 = new Purchase(imported, taxExempt, itemAmount, 47.50);
        Purchase purchase2 = new Purchase(imported, taxExempt, itemAmount, 27.99);
        Purchase purchase3 = new Purchase(imported, taxExempt, itemAmount, 10.00);
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



}
