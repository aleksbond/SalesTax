import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PurchaseTest {
    private static  int itemAmount = 2;
    private static boolean imported = true;
    private static boolean taxExempt = false;
    private static double price = 12.49;
    private static double salesTax = 1.56;
    private static double importedTax = .67;

    @Test
    public void testPurchase() throws Exception{
        Purchase purchase = new Purchase(imported, taxExempt, itemAmount, price);
        purchase.setSalesTax(salesTax);
        purchase.setImportedTax(importedTax);
        assertThat(purchase.getIsImported(), is(imported));
        assertThat(purchase.getIsTaxExempt(), is(taxExempt));
        assertThat(purchase.getItemAmount(), is(itemAmount));
        assertThat(purchase.getPrice(), is(price));
        assertThat(purchase.getSalesTax(), is(salesTax));
        assertThat(purchase.getImportedTax(), is(importedTax));
    }



}
