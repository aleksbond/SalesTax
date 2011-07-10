import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculateTaxesTest extends CalculateTaxes{

    @Test
    public void testCalculateOnlySalesTax() throws  Exception{
        assertThat(calculateOnlySalesTax(14.99), is(1.5));
        assertThat(calculateOnlySalesTax(18.99), is(1.9));
        assertThat(calculateOnlySalesTax(10.00), is(1.0));
    }

    @Test
    public void testCalculateOnlyImportedTax() throws Exception{
        assertThat(calculateOnlyImportedTax(11.25), is(.6));
        assertThat(calculateOnlyImportedTax(10.00), is(.5));
    }

}
