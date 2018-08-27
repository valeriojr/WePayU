package Employee;

import Util.Calendar;

public class MonthlyPaymentSchedule extends PaymentSchedule{
    private int payday;

    public MonthlyPaymentSchedule(int payday){
        this.payday = payday;
    }

    @Override
    public int getPayDay() {
        return payday == -1? Calendar.getInstance().lastBussinessDay() : payday;
    }

    @Override
    public String toString() {
        return String.format("Mensal %d", payday);
    }
}
