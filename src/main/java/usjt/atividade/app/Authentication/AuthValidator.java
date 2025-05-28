package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Exceptions.ValidationException;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.ValidatorUtils.isEmailValid;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class AuthValidator {

    public static void validateAuthenticate(AuthenticateRequest request){
        if (isStringNullOrEmpty(request.getEmail()) || isStringNullOrEmpty(request.getPassword())) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }

        if (!isEmailValid(request.getEmail())) {
            throw new ValidationException(ERROR_INVALID_EMAIL);
        }
    }

    public static void validatePasswordRecovery(String email){
        if (isStringNullOrEmpty(email)) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }

        if (!isEmailValid(email)) {
            throw new ValidationException(ERROR_INVALID_EMAIL);
        }
    }

}
