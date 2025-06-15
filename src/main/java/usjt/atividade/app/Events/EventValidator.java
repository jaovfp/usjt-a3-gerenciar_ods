package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Exceptions.ValidationException;

import static usjt.atividade.common.MessageConstants.ERROR_EMPTY_FIELDS;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventValidator {

    public static void validateCreateEventRequestDto(CreateEventRequestDto request) {
        if (request == null) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }

        if (request.getUserId() == null ||
                isStringNullOrEmpty(request.getPostalCode()) ||
                isStringNullOrEmpty(request.getState()) ||
                isStringNullOrEmpty(request.getAddressLine()) ||
                isStringNullOrEmpty(request.getCity()) ||
                isStringNullOrEmpty(request.getEventDescription()) ||
                request.getEventDate() == null ||
                isStringNullOrEmpty(request.getEventName()) ||
                request.getOdsId() == null) {

            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }
    }

}
