package test;

import main.Item;
import main.ProcessDataFromFile;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ItemTest {
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
    private static int decimalPlaces = 2;


    @Test
    public void testPurchase() throws Exception{
        Item item = new Item(imported, taxExempt, itemAmount, price, taxes);
        assertThat(item.getIsImported(), is(imported));
        assertThat(item.getIsTaxExempt(), is(taxExempt));
        assertThat(item.getItemAmount(), is(itemAmount));
        assertThat(item.getPrice(), is(price));
    }

    @Test
    public void testShouldOnlyReturnSalesTax() throws  Exception{
        Item item1 = new Item(imported, taxExempt, itemAmount, 14.99, taxes);
        Item item2 = new Item(imported, taxExempt, itemAmount, 18.99, taxes);
        Item item3 = new Item(imported, taxExempt, itemAmount, 10.00, taxes);
        item1.calculateTaxes(SALES_TAX);
        item2.calculateTaxes(SALES_TAX);
        item3.calculateTaxes(SALES_TAX);
        assertThat(item1.getTaxes(), is(1.5));
        assertThat(item2.getTaxes(), is(1.9));
        assertThat(item3.getTaxes(), is(1.0));
    }

    @Test
    public void testShouldOnlyReturnImportedTax() throws Exception{
        Item item1 = new Item(imported, taxExempt, itemAmount, 11.25, taxes);
        Item item2 = new Item(imported, taxExempt, itemAmount, 10.00,taxes);
        item1.calculateTaxes(IMPORTED_TAX);
        item2.calculateTaxes(IMPORTED_TAX);
        assertThat(item1.getTaxes(), is(.6));
        assertThat(item2.getTaxes(), is(.5));
    }

    @Test
    public void testShouldReturnSalesAndImportedTax() throws Exception{
        Item item1 = new Item(imported, taxExempt, itemAmount, 47.50,taxes);
        Item item2 = new Item(imported, taxExempt, itemAmount, 27.99, taxes);
        Item item3 = new Item(imported, taxExempt, itemAmount, 10.00, taxes);
        item1.calculateTaxes(SALES_IMPORTED_TAX);
        item2.calculateTaxes(SALES_IMPORTED_TAX);
        item3.calculateTaxes(SALES_IMPORTED_TAX);
        assertThat(item1.getTaxes(), is(7.15));
        assertThat(item2.getTaxes(), is(4.2));
        assertThat(item3.getTaxes(), is(1.50));
    }

    @Test
    public void testSetPurchaseValues() throws Exception{
        ProcessDataFromFile processDataFromFile = new ProcessDataFromFile();
        Item item = new Item();
        item.setPurchaseValues(PURCHASE1);
        assertThat(processDataFromFile.isImported(PURCHASE1), is(item.getIsImported()));
        assertThat(processDataFromFile.isTaxExempt(PURCHASE1), is(item.getIsTaxExempt()));
        assertThat(processDataFromFile.getNumberOfItems(PURCHASE1), is(item.getItemAmount()));
        assertThat(processDataFromFile.getPrice(PURCHASE1),is(item.getPrice()));
    }

    @Test
    public void testSetPurchaseTax() throws Exception{
        Item item = new Item();
        item = item.determinePurchaseTax(PURCHASE1);
        assertThat(item.getIsImported(), is(false));
        assertThat(item.getIsTaxExempt(), is(true));
        assertThat(item.getItemAmount(), is(1));
        assertThat(item.getPrice(), is(12.49));
        assertThat(item.getTaxes(), is(0.0));
    }

    @Test
    public void testTotalPriceAndTax() throws Exception{
        Item item1 = new Item();
        item1 = item1.determinePurchaseTax(PURCHASE1);
        Item item2 = new Item();
        item2 = item2.determinePurchaseTax(PURCHASE2);
        Item item3 = new Item();
        item3 = item3.determinePurchaseTax(PURCHASE3);
        Item item4 = new Item();
        item4 = item4.determinePurchaseTax(PURCHASE4);
        Item item5 = new Item();
        item5 = item5.determinePurchaseTax(PURCHASE5);
        Item item6 = new Item();
        item6 = item6.determinePurchaseTax(PURCHASE6);
        assertThat(item1.getTotalPriceAndTax(), is(new BigDecimal(12.49).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
        assertThat(item2.getTotalPriceAndTax(), is(new BigDecimal(16.49).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
        assertThat(item3.getTotalPriceAndTax(), is(new BigDecimal(.85).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
        assertThat(item4.getTotalPriceAndTax(), is(new BigDecimal(11.85).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
        assertThat(item5.getTotalPriceAndTax(), is(new BigDecimal(54.65).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
        assertThat(item6.getTotalPriceAndTax(), is(new BigDecimal(20.89).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
    }



}
