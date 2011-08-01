package test;

import main.Basket;
import main.Item;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasketTest {

    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";

    private static String PURCHASE7 = "1 imported bottle of perfume: 27.99";
    private static String PURCHASE8 = "1 bottle of perfume: 18.99";
    private static String PURCHASE9 = "1 packet of headache pills: 9.75";
    private static String PURCHASE10 = "1 imported box of chocolates: 11.25";
    private static int decimalPlaces = 2;
    Basket basket = new Basket();

    @Test
    public void testShouldOutputPurchaseWithTotalPriceAndTaxes()throws Exception{
        Item item = new Item();
        item.determinePurchaseTax(PURCHASE6);
        String finalPurchase = basket.getFinalPurchaseWithTaxesIncluded(PURCHASE6, item);
        assertTrue(finalPurchase.contains("1 bottle of perfume at 20.89"));
    }

    @Test
    public void testShouldGenerateListOfPurchases() throws Exception{
        List<String> purchasesFromFile = new ArrayList<String>();
        purchasesFromFile.add(PURCHASE1);
        purchasesFromFile.add(PURCHASE2);
        purchasesFromFile.add(PURCHASE3);
        Basket basket = new Basket();
        basket.generateListOfPurchases(purchasesFromFile);
        assertThat(basket.items.get(0).getPrice(), is(12.49));
        assertThat(basket.items.get(1).getPrice(), is(14.99));
        assertThat(basket.items.get(2).getPrice(), is(.85));
        assertThat(basket.items.size(), is(basket.finalPurchases.size()));
    }

    @Test
    public void testShouldCalculateTotalTaxes() throws Exception{
        List<String> purchasesFromFile = new ArrayList<String>();
        purchasesFromFile.add(PURCHASE1);
        purchasesFromFile.add(PURCHASE2);
        purchasesFromFile.add(PURCHASE3);
        Basket basket = new Basket();
        basket.generateListOfPurchases(purchasesFromFile);
        basket.calculateTotalTaxes();
        assertThat(basket.getTotalTax(), is(new BigDecimal(1.50).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));

        List<String> purchasesFromFile2 = new ArrayList<String>();
        purchasesFromFile2.add(PURCHASE7);
        purchasesFromFile2.add(PURCHASE8);
        purchasesFromFile2.add(PURCHASE9);
        purchasesFromFile2.add(PURCHASE10);
        Basket basket2 = new Basket();
        basket2.generateListOfPurchases(purchasesFromFile2);
        basket2.calculateTotalTaxes();
        assertThat(basket2.getTotalTax(), is(new BigDecimal(6.70).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
    }

    @Test
    public void testShouldCalculateTotalCost() throws IOException {
        List<String> purchasesFromFile = new ArrayList<String>();
        purchasesFromFile.add(PURCHASE1);
        purchasesFromFile.add(PURCHASE2);
        purchasesFromFile.add(PURCHASE3);
        Basket basket = new Basket();
        basket.generateListOfPurchases(purchasesFromFile);
        basket.calculateTotalCost();
        assertThat(basket.getTotalCost(), is(new BigDecimal(29.83).setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP)));
    }

    @Test
    public void testShouldWritePurchasesToFile()throws Exception{
        List<String> purchasesFromFile = new ArrayList<String>();
        purchasesFromFile.add(PURCHASE1);
        purchasesFromFile.add(PURCHASE2);
        purchasesFromFile.add(PURCHASE3);
        Basket basket = new Basket();
        File outputFile = new File("src/output/outputTest.txt");
        if(outputFile.exists())
            outputFile.delete();
        outputFile.createNewFile();
        Writer output = new BufferedWriter(new FileWriter(outputFile));
        basket.writePurchasesToFile(output,purchasesFromFile);
        output.close();
        assertTrue(outputFile.length()> 0);
    }
}
