
public class Purchase {
    boolean isImported;
    boolean isTaxExempt;
    int itemAmount;
    double price;

    public void Purchase(boolean isImported, boolean isTaxExempt, int itemAmount, double price){
        this.isImported = isImported;
        this.isTaxExempt = isTaxExempt;
        this.itemAmount = itemAmount;
        this.price = price;
    }

    public void Purchase(){};

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
}
