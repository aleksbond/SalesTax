import java.text.DecimalFormat;

public class CalculateTaxes {

    private static double SALES_TAX = .1;
    private static double IMPORTED_TAX = .05;
    private static double ROUND_INT = .5;
    private static double HUNDRED = 100.0;
    private static double ROUNDING_DECIMAL = .05;


    public double calculateOnlySalesTax(double price) {
        int priceInPennies = (int)(price*HUNDRED);
        int taxesInPennies = (int)(priceInPennies*SALES_TAX + ROUND_INT);
        double taxes = ((double)taxesInPennies)/HUNDRED;
        double roundedTaxes = Math.ceil(taxes/ROUNDING_DECIMAL)*ROUNDING_DECIMAL;
        return Math.floor(roundedTaxes*HUNDRED)/HUNDRED;
    }
    public double calculateOnlyImportedTax(double price) {
        int priceInPennies = (int)(price*HUNDRED);
        int taxesInPennies = (int)(priceInPennies*IMPORTED_TAX + ROUND_INT);
        double taxes = ((double)taxesInPennies)/HUNDRED;
        double roundedTaxes = Math.ceil(taxes/ROUNDING_DECIMAL)*ROUNDING_DECIMAL;
        return Math.floor(roundedTaxes*HUNDRED)/HUNDRED;
    }

}
