package test;

import main.Basket;
import main.BasketFactory;
import main.ReadAndWriteToFile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.io.Writer;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReadAndWriteToFileTest {
    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";
    ReadAndWriteToFile readAndWriteToFile;

    @Mock
    private Basket basket;

    @Mock
    private BasketFactory basketFactory;

    @Before
    public void setUp(){
        initMocks(this);
        readAndWriteToFile = new ReadAndWriteToFile(basketFactory);
    }

    @Test
    public void testReadLinesFromFile() throws Exception {
        List<String> input1 = readAndWriteToFile.readLinesFromFile("src/input/input1.txt");
        String line1 = input1.get(0);
        String line2 = input1.get(1);
        String line3 = input1.get(2);
        assertThat(line1.equals(PURCHASE1), is(true));
        assertThat(line2.equals(PURCHASE2), is(true));
        assertThat(line3.equals(PURCHASE3), is(true));
    }

    @Test
    public void testWriteToOutputFile() throws Exception{
        when(basketFactory.getProcessPurchases()).thenReturn(basket);
        readAndWriteToFile.writeToOutputFile();
        verify(basket, atLeast(3)).writePurchasesToFile(Matchers.<Writer>any(), Matchers.<List<String>>any());
    }
}
