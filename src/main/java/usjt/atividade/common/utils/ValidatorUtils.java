package usjt.atividade.common.utils;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class ValidatorUtils {
    public static boolean isStringNullOrEmpty(String str){
        return isNull(str) || str.trim().isEmpty();
    }
}
