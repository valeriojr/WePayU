package Documents;

import Util.Dates.DateFormatter;

import java.util.Date;

public class SaleResult {
    private double totalValue;
    private Date date;

    public SaleResult(double totalValue){
        this.totalValue = totalValue;
        this.date = new Date();
    }

    public double getTotalValue() {
        return totalValue;
    }

    @Override
    public String toString() {
        return String.format("Data da venda: %s\nValor total: %.2fR$\n", DateFormatter.format(date), totalValue);
    }
}
