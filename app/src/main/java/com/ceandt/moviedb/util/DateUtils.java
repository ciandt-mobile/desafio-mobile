package com.ceandt.moviedb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "PT"));

    public static String formatDate(final String date) {
        return DATE_FORMAT.format(new Date(date.replace("-", "/")));
    }

    public static String getYear(final String date) {
        try {
            Date parsedDate = DATE_FORMAT.parse(formatDate(date));
            if (parsedDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parsedDate);
                return String.valueOf(calendar.get(Calendar.YEAR));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}