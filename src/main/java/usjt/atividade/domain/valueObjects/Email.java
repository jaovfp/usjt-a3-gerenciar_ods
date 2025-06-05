package usjt.atividade.domain.valueObjects;

import usjt.atividade.app.Exceptions.ValidationException;

import java.util.regex.Pattern;

import static usjt.atividade.common.MessageConstants.ERROR_INVALID_EMAIL;

public class Email {

    private final String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    public Email(String value) {
        if (!isValid(value)){
            throw new ValidationException(ERROR_INVALID_EMAIL);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

}
