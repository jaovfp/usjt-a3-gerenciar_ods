package usjt.atividade.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

}
