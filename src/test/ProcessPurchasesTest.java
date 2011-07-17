package test;

import main.ProcessPurchases;
import main.Purchase;
import org.junit.Test;
import sun.tools.tree.ReturnStatement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProcessPurchasesTest{

    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";
    ProcessPurchases processPurchases = new ProcessPurchases();

    @Test
    public void testShouldOutputPurchaseWithTotalPriceAndTaxes()throws Exception{
        Purchase purchase = new Purchase();
        purchase.determinePurchaseTax(PURCHASE6);
        String finalPurchase = processPurchases.getFinalPurchaseWithTaxesIncluded(PURCHASE6, purchase);
        assertTrue(finalPurchase.contains("1 bottle of perfume at 20.89"));
    }

    @Test
    public void testShouldGenerateListOfPurchases() throws Exception{
        List<String> purchasesFromFile = new ArrayList<String>();
        purchasesFromFile.add(PURCHASE1);
        purchasesFromFile.add(PURCHASE2);
        purchasesFromFile.add(PURCHASE3);
        ProcessPurchases processPurchases = new ProcessPurchases();
        processPurchases.generateListOfPurchases(purchasesFromFile);
        assertThat(processPurchases.purchases.get(0).getPrice(), is(12.49));
        assertThat(processPurchases.purchases.get(1).getPrice(), is(14.99));
        assertThat(processPurchases.purchases.get(2).getPrice(), is(.85));
    }

    @Test
    public void testShouldCalculateTotalTaxes() throws Exception{

    }
}
