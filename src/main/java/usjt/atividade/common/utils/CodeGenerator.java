package usjt.atividade.common.utils;

import java.security.SecureRandom;

public class CodeGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final String DIGITS = "0123456789";

    private CodeGenerator() {
    }

    public static String generateNumericCode(int length) {
        return generateCode(DIGITS, length);
    }

    private static String generateCode(String characters, int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
}
