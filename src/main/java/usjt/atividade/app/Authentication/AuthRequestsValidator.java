package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Authentication.dto.ResetPasswordRequest;
import usjt.atividade.app.Exceptions.ValidationException;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.ValidatorUtils.*;

public class AuthRequestsValidator {

    public static void validateAuthenticateRequest(AuthenticateRequest request){
        if (isStringNullOrEmpty(request.getEmail()) || isStringNullOrEmpty(request.getPassword())) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }
    }

    public static void validatePasswordRecoveryRequest(String email){
        if (isStringNullOrEmpty(email)) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }
    }

    public static void validateResetPasswordRequest(ResetPasswordRequest request){
        if (isStringNullOrEmpty(request.getPinCode()) || isStringNullOrEmpty(request.getPassword()) || isStringNullOrEmpty(request.getRecoveryId().toString())) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }
    }

}
