package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.app.Exceptions.UnauthorizedException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.Exceptions.ValidationException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.domain.valueObjects.UserType;

import static usjt.atividade.common.MessageConstants.ERROR_EMPTY_FIELDS;
import static usjt.atividade.common.MessageConstants.ONLY_PENDING_CAN_APPROVE_REJECT;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventRequestValidator {

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

    public static void validateStatusChange(UserType userType, User actor, EventRequest eventRequest, EventRequestStatus newStatus) {
        EventRequestStatus currentStatus = eventRequest.getStatus();

        switch (newStatus) {
            case APPROVED:
            case REJECTED:
                if (!UserType.ADMIN.equals(userType)) {
                    throw new UnauthorizedException(MessageConstants.ONLY_ADMINS_CAN_APPROVE_REJECT);
                }
                if (!EventRequestStatus.PENDING.equals(currentStatus)) {
                    throw new UnprocessableEntityException(MessageConstants.ONLY_PENDING_CAN_APPROVE_REJECT);
                }
                break;

            case CANCELED:
                if (!eventRequest.getRequestedBy().equals(actor)) {
                    throw new UnauthorizedException(MessageConstants.ONLY_CREATOR_CAN_CANCEL);
                }
                if (!EventRequestStatus.PENDING.equals(currentStatus)) {
                    throw new UnprocessableEntityException(MessageConstants.ONLY_PENDING_CAN_CANCEL);
                }
                break;

            default:
                throw new UnprocessableEntityException(MessageConstants.STATUS_TRANSITION_NOT_ALLOWED);
        }
    }

}
