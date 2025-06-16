package usjt.atividade.app.User;

import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.Exceptions.ValidationException;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;

import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.ValidatorUtils.*;

public class UserValidator {

    public static void validateCreateRequestIsNull(CreateUserRequest request) {
        if (isStringNullOrEmpty(request.getFullname()) || isStringNullOrEmpty(request.getEmail()) || isStringNullOrEmpty(request.getPassword())) {
            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_EMPTY_FIELDS);
        }
    }

    public static void validateUpdateUserRequestIsNull(UpdateUserRequest request) {
        if (isStringNullOrEmpty(request.getFullname()) ||
                isStringNullOrEmpty(request.getEmail()) ||
                request.getBirthDate() == null ||
                isStringNullOrEmpty(request.getCpf()) ||
                isStringNullOrEmpty(request.getPhoneNumber()) ||
                isStringNullOrEmpty(request.getAddressLine()) ||
                isStringNullOrEmpty(request.getCity()) ||
                isStringNullOrEmpty(request.getState()) ||
                isStringNullOrEmpty(request.getPostalCode())) {

            throw new ValidationException(GENERIC_CREATE_USER_ERROR + ERROR_EMPTY_FIELDS);
        }
    }

    public static void validateUserExists(boolean userExists){
        if (userExists){
            throw new UnprocessableEntityException(ERROR_EMAIL_ALREADY_EXISTS);
        }
    }

}
