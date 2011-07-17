package test;

import main.ReadAndWriteToFile;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReadAndWriteToFileTest {
    private static String PURCHASE1 = "1 book at 12.49";
    private static String PURCHASE2 = "1 music CD at 14.99";
    private static String PURCHASE3 = "1 chocolate bar at 0.85";
    private static String PURCHASE4 = "1 box of imported chocolates at 11.25";
    private static String PURCHASE5 = "1 imported bottle of perfume at 47.50";
    private static String PURCHASE6 = "1 bottle of perfume at 18.99";
    ReadAndWriteToFile readAndWriteToFile = new ReadAndWriteToFile();

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
}
