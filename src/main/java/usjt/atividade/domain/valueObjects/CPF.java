package usjt.atividade.domain.valueObjects;

import java.util.regex.Pattern;

public class CPF {

    private final String value;

    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$");

    public CPF(String value) {
        String normalized = normalize(value);
        if (!isFormatValid(normalized)) {
            // lançar exceção
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    private static String normalize(String cpf) {
        return cpf.replaceAll("[^\\d]", "");
    }

    private static boolean isFormatValid(String cpf) {
        return CPF_PATTERN.matcher(cpf).matches();
    }

}
