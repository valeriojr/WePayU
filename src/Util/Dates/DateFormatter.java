package Util.Dates;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String format(Date date){
        if(date != null){
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        }else {
            return "--/--/----";
        }
    }

    public static String timeFormat(Date date){
        if(date != null){
            return new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
        }else {
            return "--:--:-- --/--/----";
        }
    }
}
