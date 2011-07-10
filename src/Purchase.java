import java.rmi.server.ObjID;

public class Purchase {
    boolean isImported;
    boolean isTaxExempt;
    int itemAmount;
    double price;
    double salesTax = 0.0;
    double importedTax = 0.0;

    public Purchase(boolean isImported, boolean isTaxExempt, int itemAmount, double price){
        this.isImported = isImported;
        this.isTaxExempt = isTaxExempt;
        this.itemAmount = itemAmount;
        this.price = price;
    }

    public Purchase(){}

    public boolean getIsImported(){
        return isImported;
    }

    public boolean getIsTaxExempt(){
        return isTaxExempt;
    }

    public int getItemAmount(){
        return itemAmount;
    }

    public double getPrice(){
        return price;
    }

    public double getSalesTax(){
        return salesTax;
    }

    public double getImportedTax(){
        return importedTax;
    }

    public void setSalesTax(double salesTax){
        this.salesTax = salesTax;
    }

    public void setImportedTax(double importedTax){
        this.importedTax = importedTax;
    }

    public double getTotalPriceAndTax(){
        return (price + salesTax + importedTax);
    }
}
