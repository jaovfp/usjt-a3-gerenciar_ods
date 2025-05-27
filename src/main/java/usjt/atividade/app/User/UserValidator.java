package usjt.atividade.app.User;

import usjt.atividade.app.Exceptions.ValidationException;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.ValidatorUtils.*;

public class UserValidator {

    public static void validateCreate(CreateUserRequest request) {
        if (isStringNullOrEmpty(request.getUsername()) || isStringNullOrEmpty(request.getEmail()) || isStringNullOrEmpty(request.getPassword())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_EMPTY_FIELDS);
        }

        if (isEmailValid(request.getEmail())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_INVALID_EMAIL);
        }

        if (isValidPasswordLength(request.getPassword())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_PASSWORD_LENGTH);
        }

        if (passwordHasUppercase(request.getPassword())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_PASSWORD_UPPERCASE);
        }

        if (passwordHasSpecialCharacter(request.getPassword())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_PASSWORD_SPECIAL_CHAR);
        }
    }

}
