package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Authentication.dto.ResetPasswordRequest;
import usjt.atividade.app.Exceptions.ValidationException;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.ValidatorUtils.*;

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

    public static void validateResetPassword(ResetPasswordRequest request){
        if (isStringNullOrEmpty(request.getPinCode()) || isStringNullOrEmpty(request.getPassword()) || isStringNullOrEmpty(request.getRecoveryId().toString())) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }

        if (request.getPinCode().length() != 6) {
            throw new ValidationException(PIN_CODE_ERROR_LENGHT);
        }

        if (!passwordHasUppercase(request.getPassword())){
            throw new ValidationException(ERROR_PASSWORD_UPPERCASE);
        }

        if(!passwordHasSpecialCharacter(request.getPassword())){
            throw new ValidationException(ERROR_PASSWORD_SPECIAL_CHAR);
        }
    }

}
