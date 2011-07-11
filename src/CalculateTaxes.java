public class CalculateTaxes {

    private static double SALES_TAX = .1;
    private static double IMPORTED_TAX = .05;
    private static double ROUND_INT = .5;
    private static double HUNDRED = 100.0;
    private static double ROUNDING_DECIMAL = .05;


    public double calculateOnlySalesTax(double price) {
        return calculateFinalTaxes(price, SALES_TAX);
    }

    public double calculateOnlyImportedTax(double price) {
        return calculateFinalTaxes(price, IMPORTED_TAX);
    }

    public double calculateSalesAndImportedTax(double price) {
        return calculateFinalTaxes(price, SALES_TAX + IMPORTED_TAX);
    }

    private double calculateFinalTaxes(double price, double taxType) {
        int priceInPennies = (int)(price*HUNDRED);
        int taxesInPennies = (int)(priceInPennies*taxType + ROUND_INT);
        double taxes = ((double)taxesInPennies)/HUNDRED;
        double roundedTaxes = Math.ceil(taxes/ROUNDING_DECIMAL)*ROUNDING_DECIMAL;
        return Math.floor(roundedTaxes*HUNDRED)/HUNDRED;
    }

}
