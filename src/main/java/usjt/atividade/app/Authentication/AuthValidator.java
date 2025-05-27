package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Exceptions.ValidationException;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.ValidatorUtils.isEmailValid;
import static usjt.atividade.common.ValidatorUtils.isStringNullOrEmpty;

public class AuthValidator {

    public static void validateAuthenticate(AuthenticateRequest request){
        if (isStringNullOrEmpty(request.getEmail()) || isStringNullOrEmpty(request.getPassword())) {
            throw new ValidationException(GENERIC_AUTHENTICATE_ERROR + ERROR_EMPTY_FIELDS);
        }

        if (isEmailValid(request.getEmail())) {
            throw new ValidationException(GENERIC_AUTHENTICATE_ERROR + ERROR_INVALID_EMAIL);
        }
    }

}
