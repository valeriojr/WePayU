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
    public String toString() {
        return String.format("Tipo: Assalariado\nSalário: %.2f\n", salary);
    }
}
