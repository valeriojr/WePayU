package Documents;

import Util.Dates.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timecard {
    private int hours;
    private Date arrival, departure;

    public Timecard(){
        this.arrival = new Date();
    }

    public void submit(int hours){
        this.hours = hours;
        this.departure = new Date();
    }

    @Override
    public String toString() {
        String formatString = "Chegada: %s\nSaída: %s\nHoras trabalhadas: %s\n";
        return String.format(formatString, DateFormatter.timeFormat(arrival), DateFormatter.timeFormat(departure), hours);
    }

    public boolean submitted() {
        return departure == null;
    }
}
