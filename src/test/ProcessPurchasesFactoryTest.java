package test;

import main.ProcessPurchases;
import org.junit.Test;

public class ProcessPurchasesFactoryTest {

    @Test
    public void testFactoryMethod(){
        ProcessPurchasesFactory  processPurchasesFactory = new ProcessPurchasesFactory();
        ProcessPurchases processPurchases = processPurchasesFactory.getProcessPurchases();
        assertEquals(ProcessPurchases.class, processPurchases.getClass());
    }
}
