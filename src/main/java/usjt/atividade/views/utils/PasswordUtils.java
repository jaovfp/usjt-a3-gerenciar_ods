package usjt.atividade.views.utils;

import java.util.Arrays;

public class PasswordUtils {

    public static String convertPassword(CustomPasswordField passwordField) {
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        Arrays.fill(passwordChars, '\0');
        return password;
    }
}
