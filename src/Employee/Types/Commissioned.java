package Employee.Types;

import Employee.EmployeeType;

public class Commissioned extends Salaried {
    private double commissionRate, sales;

    public Commissioned(double salary) {
        super(salary);
        this.sales = 0;
    }

    @Override
    public double getPayment() {
        return commissionRate * sales + (salary / 2);
    }

    public void submit(double sale){
        this.sales += sale * commissionRate;
    }

    @Override
    public String toString() {
        return String.format("Tipo: Comissionado\nTaxa de comissão: %.2f %%\nVendas: %f", commissionRate, sales);
    }
}
