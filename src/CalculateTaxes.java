

public class CalculateTaxes {

    private static double SALESTAX = .1;
    private static double ROUND = .5;

    public double calculateOnlySalesTax(double price) {
        int priceInPennies = (int)(price*100.0);
        int taxesInPennies = (int)(priceInPennies*SALESTAX + ROUND);
        double taxes = ((double)taxesInPennies)/100;
        return taxes;
    }


}
