package Employee;

import Util.Calendar;

public class WeeklyPaymentSchedule extends PaymentSchedule {
    private int period;
    private int lastPayment;
    private Calendar.Day day;

    public WeeklyPaymentSchedule(int period, Calendar.Day day){
        this.period = period;
        this.day = day;
    }

    @Override
    public int getPayDay() {
        if((lastPayment - Calendar.getInstance().getCurrentAbsoluteDate())/7 >= period &&
                Calendar.getInstance().getCurrentDay() == day){
            lastPayment = Calendar.getInstance().getCurrentDate();
            return Calendar.getInstance().getCurrentDate();
        }

        return -1;
    }

    @Override
    public String toString() {
        return String.format("Semanal %d %s", period, day);
    }
}
