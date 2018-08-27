package Employee.Types;

import Employee.EmployeeType;

public class Salaried implements EmployeeType {
    double salary;

    public Salaried(double salary){
        this.salary = salary;
    }

    @Override
    public double getPayment() {
        return salary;
    }

    @Override
    public boolean isPayDay() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Assalariado | Salário: %.2f/mês", salary);
    }
}
