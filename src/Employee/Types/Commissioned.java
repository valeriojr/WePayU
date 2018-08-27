package Employee.Types;

import Documents.SaleResult;

import java.util.Date;
import java.util.Stack;

public class Commissioned extends Salaried {
    private double commissionRate, sales;
    private Stack<SaleResult> saleResults;

    public Commissioned(double salary, double commissionRate) {
        super(salary);
        this.commissionRate = commissionRate;
        this.sales = 0;
        this.saleResults = new Stack<>();
    }

    @Override
    public double getPayment() {
        return commissionRate * sales + (salary / 2);
    }

    @Override
    public boolean isPayDay() {
        return false;
    }

    public void submit(SaleResult result){
        this.sales += result.getTotalValue();

        saleResults.push(result);
    }

    @Override
    public String toString() {
        return String.format("Comissionado | Taxa de comissão: %.2f %% | Vendas: %f", commissionRate, sales);
    }
}
