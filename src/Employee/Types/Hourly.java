package Employee.Types;

import Documents.Timecard;
import Employee.EmployeeType;
import Menu.MenuManager;
import Menu.MessageDialog;
import Util.Input.Input;

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
        if(timecards.empty()){
            timecards.push(timecard);
            return;
        }
        if(timecards.peek().alreadySubmitted()) {
            timecards.push(timecard);
        }else {
            MenuManager.getInstance().startNewMenu(new MessageDialog("Submeta o cartão anterior"));
        }
    }

    @Override
    public double getPayment() {
        return (hours + extraHours * 1.5) * hourlyWage;
    }

    @Override
    public boolean isPayDay() {
        return false;
    }

    public void submitTimecard(){
        if(timecards.empty()){
            MenuManager.getInstance().startNewMenu(new MessageDialog("Cartão de ponto não encontrado"));
            return;
        }
        if(timecards.peek().alreadySubmitted()){
            MenuManager.getInstance().startNewMenu(new MessageDialog("Cartão de ponto já submetido"));
            return;
        }
        timecards.peek().submit(Input.getInt("Horas trabalhadas"));
        if(timecards.peek().getHours() > 8){
            this.extraHours += timecards.peek().getHours() - 8;
        }
        this.hours += timecards.peek().getHours();
    }

    @Override
    public String toString() {
        return String.format("Horista | Salário Horário: %.2f R$/h | Horas trabalhadas: %d h", hourlyWage, hours);
    }
}
