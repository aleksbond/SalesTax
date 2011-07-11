
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ProcessDataFromFileTest extends ProcessDataFromFile {

    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";

    @Test
    public void testReadLinesFromFile() throws Exception {
        List<String> input1 = readLinesFromFile("/Users/Thoughtworks/SalesTax/src/input1.txt");
        String line1 = input1.get(0);
        String line2 = input1.get(1);
        String line3 = input1.get(2);
        assertThat(line1.equals(PURCHASE1), is(true));
        assertThat(line2.equals(PURCHASE2), is(true));
        assertThat(line3.equals(PURCHASE3), is(true));
    }

    @Test
    public void testGetNumberOfItems() throws Exception {
       int numOfItem = getNumberOfItems("1 chocolate bar at 0.85");
       assertThat(numOfItem, is(1));
       numOfItem = getNumberOfItems("5 chocolate bar at 0.85");
       assertThat(numOfItem, is(5));
    }

    @Test
    public void testIsTaxExempt() throws Exception {
        boolean taxExempt = isTaxExempt(PURCHASE1);
        assertThat(taxExempt, is(true));
        taxExempt = isTaxExempt(PURCHASE2);
        assertThat(taxExempt, is(false));
    }

    @Test
    public void testIsImported() throws Exception{
        boolean imported = isImported(PURCHASE1);
        assertThat(imported, is(false));
        imported = isImported(PURCHASE4);
        assertThat(imported, is(true));
    }

    @Test
    public void testGetPrice() throws Exception{
        double price = getPrice(PURCHASE1);
        assertThat(price, is(12.49));
    }

    @Test
    public void testLoopThroughFiles() throws Exception{
        ProcessDataFromFile readFile = mock(ProcessDataFromFile.class);
        Purchase purchase = mock(Purchase.class);
        loopThroughFiles();


    }


}
