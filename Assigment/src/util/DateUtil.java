package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-mm-dd";
    public static Date stringToDate(String s) throws ParseException {

        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.parse(s);

    }
}
