package Employee.Types;

import Employee.EmployeeType;

public class Hourly implements EmployeeType {
    int hours;
    double extraHours;
    double hourlyWage;

    public Hourly(double hourlyWage){
        this.hours = 0;
        this.extraHours = 0;
        this.hourlyWage = hourlyWage;
    }

    @Override
    public double getPayment() {
        return (hours + extraHours * 1.5) * hourlyWage;
    }

    public void submit(int hours){
        if(hours > 8){
            this.extraHours += hours - 8;
        }
        this.hours += hours;
    }

    @Override
    public String toString() {
        return String.format("Tipo: Horista\nSalário Horário: %.2f R$/h\nHoras trabalhadas: %d h", hourlyWage, hours);
    }
}
