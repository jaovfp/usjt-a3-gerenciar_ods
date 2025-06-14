package usjt.atividade.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static Timestamp toSqlTimestamp(LocalDateTime localDateTime) {
        if (isNull(localDateTime)) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    public static String dateConverter(LocalDateTime date, String dateFormat) {
        Date convertedDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
        return outputFormat.format(convertedDate);
    }

    public static String dateConverter(LocalDate date, String dateFormat) {
        Date convertedDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
        return outputFormat.format(convertedDate);
    }

    public static String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        return formatter.format(new Date());
    }

}
