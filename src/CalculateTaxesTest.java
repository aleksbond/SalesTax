import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculateTaxesTest extends CalculateTaxes{

    @Test
    public void testCalculateOnlySalesTax() throws  Exception{
        assertThat(calculateOnlySalesTax(14.99), is(1.5));
    }

}
