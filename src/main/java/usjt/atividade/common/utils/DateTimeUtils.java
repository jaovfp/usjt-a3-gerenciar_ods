package usjt.atividade.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static LocalDate parseToLocalDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isBlank()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao converter data: " + dateStr);
            throw e;
        }
    }

    public static LocalDateTime parseToLocalDateTime(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || dateTimeStr.isBlank()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao converter data/hora: " + dateTimeStr);
            throw e;
        }
    }

    public static String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        return formatter.format(new Date());
    }

}
