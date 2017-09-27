package com.projects.vimal.santvicharan.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vimal on 9/24/17.
 */

public class DateFormatter {

    /**
     * Default constructor
     */
    public DateFormatter() {

    }


    /**
     * Convert the given Date into the provided format
     * @param date
     * @param format
     * @return
     */
    public static String convertToStringFormat(Date date, String format) {

        return new SimpleDateFormat(format).format(date);
    }
}
