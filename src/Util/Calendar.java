package Util;

import Employee.MonthlyPaymentSchedule;
import Employee.PaymentSchedule;
import Employee.WeeklyPaymentSchedule;

import java.util.Date;

public class Calendar {
    private static Calendar instance;

    enum Month {
        JAN(31), FEB(28), MAR(31), APR(30), MAY(31), JUN(30), JUL(31), AUG(31), SEP(30), OCT(31), NOV(30), DEC(31);
        private int days;

        Month(int days){
            this.days = days;
        }

        public int getDays() {
            return days;
        }

        public Month next(){
            return Month.values()[(ordinal() + 1)%12];
        }
    }

    public enum Day {
        SUN, MON, TUE, WED, THU, FRI, SAT;

        private int index;

        public Day next() {
            return Day.values()[(ordinal() + 1) % 7];
        }
    }

    private int currentDate;
    private Month currentMonth;
    private Day currentDay;
    private int currentAbsoluteDate;

    private Calendar(){
        Date now = new Date();

        currentDate = now.getDate();
        currentDay = Day.values()[now.getDay()];
        currentMonth = Month.values()[now.getMonth()];
        currentAbsoluteDate = 0;
        for(int i = 0;i <= currentMonth.ordinal();i++){
            currentAbsoluteDate += Month.values()[i].getDays();
        }
    }

    public void goToNextDay(){
        currentDate++;
        currentDay = currentDay.next();
        if(currentDate > currentMonth.getDays()){
            currentDate = 1;
            currentMonth = currentMonth.next();
        }
        currentAbsoluteDate++;
    }

    public int lastBussinessDay(){
        Day d = currentDay;
        int i;
        for(i = currentDate;i <= currentMonth.getDays();i++, d = d.next()){
            if(currentMonth.getDays() - i <= 2 && d == Day.FRI){
                return i;
            }
        }
        return i;
    }

    public int getCurrentAbsoluteDate() {
        return currentAbsoluteDate;
    }

    public Day getCurrentDay() {
        return currentDay;
    }

    public int getCurrentDate() {
        return currentDate;
    }

    public boolean matches(PaymentSchedule schedule) {
        return schedule.getPayDay() == currentDate;
    }



    public static Calendar getInstance(){
        if(instance == null){
            instance = new Calendar();
        }

        return instance;
    }
}
