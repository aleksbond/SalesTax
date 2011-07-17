package test;

import main.ProcessDataFromFile;
import main.Purchase;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ProcessDataFromFileTest{

    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";

    ProcessDataFromFile processDataFromFile = new ProcessDataFromFile();

    @Test
    public void testGetNumberOfItems() throws Exception {
       int numOfItem = processDataFromFile.getNumberOfItems(PURCHASE3);
       assertThat(numOfItem, is(1));
       numOfItem = processDataFromFile.getNumberOfItems("5 chocolate bar at 0.85");
       assertThat(numOfItem, is(5));
    }

    @Test
    public void testIsTaxExempt() throws Exception {
        boolean taxExempt = processDataFromFile.isTaxExempt(PURCHASE1);
        assertThat(taxExempt, is(true));
        taxExempt = processDataFromFile.isTaxExempt(PURCHASE2);
        assertThat(taxExempt, is(false));
    }

    @Test
    public void testIsImported() throws Exception{
        boolean imported = processDataFromFile.isImported(PURCHASE1);
        assertThat(imported, is(false));
        imported = processDataFromFile.isImported(PURCHASE4);
        assertThat(imported, is(true));
    }

    @Test
    public void testGetPrice() throws Exception{
        double price = processDataFromFile.getPrice(PURCHASE1);
        assertThat(price, is(12.49));
    }
}
