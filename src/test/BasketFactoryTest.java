package test;

import main.Basket;
import main.BasketFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasketFactoryTest {

    @Test
    public void testFactoryMethod(){
        BasketFactory basketFactory = new BasketFactory();
        Basket basket = basketFactory.getProcessPurchases();
        assertEquals(Basket.class, basket.getClass());
    }
}
