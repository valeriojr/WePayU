package Employee.Types;

import Documents.Timecard;
import Employee.EmployeeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hourly implements EmployeeType {
    int hours;
    double extraHours;
    double hourlyWage;
    Stack<Timecard> timecards;

    public Hourly(double hourlyWage){
        this.hours = 0;
        this.extraHours = 0;
        this.hourlyWage = hourlyWage;
        this.timecards = new Stack<>();
    }

    public void addTimecard(Timecard timecard) {
        if (!timecards.empty()) {
            if (timecards.peek().submitted()) {
                timecards.add(timecard);
            }
        }else {
            timecards.add(timecard);
        }
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

    public Stack<Timecard> getTimecards() {
        return timecards;
    }
}
