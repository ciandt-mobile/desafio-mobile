package br.com.ciandt.application.fellini.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUnitsUtil {

    private static final String API_DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_DEFAULT_DATE_FORMAT, Locale.getDefault());

    public static String buildDisplayDate(String sourceDate, boolean shortVersion) throws ParseException {
        simpleDateFormat.format(simpleDateFormat.parse(sourceDate));
        Calendar calendar = simpleDateFormat.getCalendar();

        if (shortVersion) {
            return String.format(Locale.getDefault(), "%d %S", calendar.get(Calendar.DAY_OF_MONTH), calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()));
        } else {
            return String.format(Locale.getDefault(), "%d %S %02d", calendar.get(Calendar.DAY_OF_MONTH), calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()), calendar.get(Calendar.YEAR));
        }
    }

    public static String convertRuntime(int runtime) {
        if (runtime <= 0) {
            return "---";
        }
        int hours = runtime / 60;
        int minutes = runtime % 60;
        return String.format(Locale.getDefault(), "%dh %02dm", hours, minutes);
    }


}
