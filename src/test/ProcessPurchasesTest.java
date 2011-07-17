package test;

import main.ProcessPurchases;
import main.Purchase;
import org.junit.Test;
import sun.tools.tree.ReturnStatement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProcessPurchasesTest{

    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";

    @Test
    public void testShouldOutputPurchaseWithTotalPriceAndTaxes()throws Exception{
        Purchase purchase = new Purchase();
        purchase.determinePurchaseTax(PURCHASE6);
        ProcessPurchases processPurchases = new ProcessPurchases();
        String finalPurchase = processPurchases.getFinalPurchaseWithTaxesIncluded(PURCHASE6, purchase);
        assertTrue(finalPurchase.contains("1 bottle of perfume at 20.89"));
    }

    @Test
    public void testShouldGenerateListOfPurchases() throws Exception{
       Purchase purchase1 = mock(Purchase.class);
       Purchase purchase2 = mock(Purchase.class);
       Purchase purchase3 = mock(Purchase.class);
       ProcessPurchases processPurchases = new ProcessPurchases();
       processPurchases.generateListOfPurchases();
       verify(purchase1).determinePurchaseTax(anyString());
    }
}
