package test;

import main.ProcessPurchases;
import main.ProcessPurchasesFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProcessPurchasesFactoryTest {

    @Test
    public void testFactoryMethod(){
        ProcessPurchasesFactory processPurchasesFactory = new ProcessPurchasesFactory();
        ProcessPurchases processPurchases = processPurchasesFactory.getProcessPurchases();
        assertEquals(ProcessPurchases.class, processPurchases.getClass());
    }
}
