package usjt.atividade.domain.valueObjects;

import java.util.regex.Pattern;

public class PhoneNumber {

    private final String value;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+55\\d{10,11}$");

    public PhoneNumber(String phone) {
        String normalized = normalize(phone);
        if (!normalized.startsWith("+55")) {
            normalized = "+55" + normalized;
        }
        if (!isValid(normalized)) {
            //lançar exceção
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    private static String normalize(String phone) {
        return phone.replaceAll("[^\\d+]", "");
    }

    private static boolean isValid(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    @Override
    public String toString() {
        String ddd = value.substring(3, 5);
        String firstPart = value.length() == 14 ? value.substring(5, 10) : value.substring(5, 9);
        String secondPart = value.length() == 14 ? value.substring(10) : value.substring(9);
        return String.format("+55 %s %s-%s", ddd, firstPart, secondPart);
    }

}
