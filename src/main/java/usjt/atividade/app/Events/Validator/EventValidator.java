package usjt.atividade.app.Events.Validator;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Events.DTO.EventSubscribeDto;
import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.app.Exceptions.UnauthorizedException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.Exceptions.ValidationException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.domain.valueObjects.UserType;

import java.time.LocalDate;

import static usjt.atividade.common.MessageConstants.ERROR_EMPTY_FIELDS;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventValidator {

    public static void validateEventRequestForCreation(EventRequest eventRequest) {
        if (eventRequest.getOds() == null) {
            throw new UnprocessableEntityException(MessageConstants.ODS_REQUIRED);
        }
        if (eventRequest.getEventName() == null || eventRequest.getEventName().isBlank()) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_NAME_REQUIRED);
        }
        if (eventRequest.getEventDescription() == null || eventRequest.getEventDescription().isBlank()) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_DESCRIPTION_REQUIRED);
        }
        if (eventRequest.getEventDate() == null || eventRequest.getEventDate().isBefore(LocalDate.now())) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_DATE_INVALID);
        }
        if (eventRequest.getAddress() == null || eventRequest.getAddress().toString().isBlank()) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_ADDRESS_REQUIRED);
        }
        if (eventRequest.getRequestedBy() == null) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_REQUESTED_BY_REQUIRED);
        }
    }

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

    public static void validateUpdateEventRequestStatusRequest(UpdateEventRequestStatusDto request) {
        if (request.getRequestId() == null || request.getActorUserId() == null || request.getNewStatus() == null) {
            throw new ValidationException(ERROR_EMPTY_FIELDS);
        }
    }

    public static void validateEventSubscribeRequest(EventSubscribeDto request) {
        if (request.getUserId() == null) {
            throw new ValidationException(MessageConstants.USER_ID_REQUIRED);
        }
        if (request.getEventId() == null) {
            throw new ValidationException(MessageConstants.EVENT_ID_REQUIRED);
        }
    }

    public static void validateEventDateBeforeToday(LocalDate eventDate) {
        if (eventDate.isBefore(LocalDate.now())) {
            throw new UnprocessableEntityException(MessageConstants.ERROR_EVENT_DATE_IN_PAST);
        }
    }

    public static void validateUserHasCompleteProfile(User user) {
        if (!user.isProfileComplete()){
            throw new UnprocessableEntityException(MessageConstants.USER_NOT_PROFILE_COMPLETE);
        };
    }
}
