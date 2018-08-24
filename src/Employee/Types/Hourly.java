package Employee.Types;

import Employee.EmployeeType;

public class Hourly implements EmployeeType {
    int hours;
    double extraHours;
    double dailyRate;

    public Hourly(double dailyRate){
        this.hours = 0;
        this.extraHours = 0;
        this.dailyRate = dailyRate;
    }

    @Override
    public double getPayment() {
        return (hours + extraHours * 1.5) * dailyRate;
    }

    public void submit(int hours){
        if(hours > 8){
            this.extraHours += hours - 8;
        }
        this.hours += hours;
    }

    @Override
    public String toString() {
        return String.format("Tipo: Horista\nDiária: %.2f\nHoras trabalhadas: %d", dailyRate, hours);
    }
}
