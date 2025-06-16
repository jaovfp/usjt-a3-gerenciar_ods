package usjt.atividade.domain.valueObjects;

import usjt.atividade.app.Exceptions.ValidationException;

import java.util.regex.Pattern;

import static usjt.atividade.common.MessageConstants.*;

public class Password {

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9]");
    private static final int MIN_LENGTH = 8;

    private final String value;

    public Password(String value) {
        if (!isValidLength(value)) {
            throw new ValidationException(ERROR_PASSWORD_LENGTH);
        }
        if (!hasUppercase(value)) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_PASSWORD_UPPERCASE);
        }
        if (!hasSpecialCharacter(value)) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_PASSWORD_SPECIAL_CHAR);
        }
        this.value = value;
    }

    private boolean isValidLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private boolean hasUppercase(String password) {
        return UPPERCASE_PATTERN.matcher(password).find();
    }

    private boolean hasSpecialCharacter(String password) {
        return SPECIAL_CHAR_PATTERN.matcher(password).find();
    }

    public String getValue() {
        return value;
    }

}
