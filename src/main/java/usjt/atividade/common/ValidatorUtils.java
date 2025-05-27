package usjt.atividade.common;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class ValidatorUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

    public static boolean isValidPasswordLength(String password) {
        return password.length() >= 8;
    }

    public static boolean passwordHasUppercase(String password) {
        return UPPERCASE_PATTERN.matcher(password).find();
    }

    public static boolean passwordHasSpecialCharacter(String password) {
        return SPECIAL_CHAR_PATTERN.matcher(password).find();
    }

    public static boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isStringNullOrEmpty(String str){
        return isNull(str) || str.trim().isEmpty();
    }

}
