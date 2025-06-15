package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.service.EventRequestService;
import usjt.atividade.domain.valueObjects.Address;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.Repository.EventRequestRepositoryImpl;
import usjt.atividade.infra.Repository.OdsRepositoryImpl;
import usjt.atividade.infra.Repository.UserRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static usjt.atividade.app.Events.EventRequestValidator.*;
import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;

public class EventRequestServiceImpl implements EventRequestService {

    private final EventRequestRepositoryImpl repository;
    private final UserRepositoryImpl userRepository;
    private final OdsRepositoryImpl odsRepository;

    public EventRequestServiceImpl() {
        this.repository = new EventRequestRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
        this.odsRepository = new OdsRepositoryImpl();
    }

    @Override
    public PaginatedResponse<EventRequest> getPaginatedEventRequests(int page, int size, EventRequestFilter filter) {
        int totalItems = repository.countEventRequestByFilter(filter);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<EventRequest> items = repository.findAllEventRequestsByFilter(offset, size, filter);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }

    @Override
    public void create(CreateEventRequestDto request) {
        validateCreateEventRequestDto(request);

        User user = findUserById(request.getUserId());
        ODS ods = findOdsById(request.getOdsId());
        LocalDate eventDate = request.getEventDate();

        validateEventDate(eventDate);
        validateExistingEvent(user.getUserId(), eventDate);

        EventRequest event = buildEventRequest(request, user, ods);
        repository.save(event);
    }

    private User findUserById(UUID userId) {
        return userRepository.findById(userId.toString())
                .orElseThrow(() -> new UnprocessableEntityException(MessageConstants.USER_NOT_FOUND));
    }

    private ODS findOdsById(UUID odsId) {
        return odsRepository.findById(odsId)
                .orElseThrow(() -> new UnprocessableEntityException(MessageConstants.ODS_NOT_FOUND));
    }

    private void validateEventDate(LocalDate eventDate) {
        if (eventDate.isBefore(LocalDate.now())) {
            throw new UnprocessableEntityException(MessageConstants.ERROR_EVENT_DATE_IN_PAST);
        }
    }

    private void validateExistingEvent(UUID userId, LocalDate eventDate) {
        if (repository.existsPendingOrApprovedByUserIdAndEventDate(userId, eventDate)) {
            throw new UnprocessableEntityException(MessageConstants.EXISTS_REQUEST_EVENT_FOR_DAY +  dateConverter(eventDate, "dd/MM/yyyy"));
        }
    }

    private EventRequest buildEventRequest(CreateEventRequestDto request, User user, ODS ods) {
        EventRequest event = new EventRequest();
        event.setRequestId(UUID.randomUUID());
        event.setRequestedBy(user);
        event.setOds(ods);
        event.setEventName(request.getEventName());
        event.setEventDescription(request.getEventDescription());
        event.setEventDate(request.getEventDate());
        event.setCreateDate(LocalDateTime.now());
        event.setStatus(EventRequestStatus.PENDING);

        Address address = new Address(
                request.getAddressLine(),
                request.getCity(),
                request.getState(),
                request.getPostalCode()
        );
        event.setAddress(address);

        return event;
    }

    @Override
    public EventRequest updateStatus(UpdateEventRequestStatusDto request){
        validateUpdateEventRequestStatusRequest(request);
        User actor = userRepository.findById(request.getActorUserId().toString())
                .orElseThrow(() -> new UnprocessableEntityException(MessageConstants.USER_NOT_FOUND));

        EventRequest eventRequest = repository.findById(request.getRequestId().toString())
                .orElseThrow(() -> new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND));

        EventRequestStatus newStatus = EventRequestStatus.valueOf(request.getNewStatus().toUpperCase());
        validateStatusChange(request.getUserType(),actor, eventRequest, newStatus);
        eventRequest.updateStatus(newStatus);
        repository.update(eventRequest);
        return eventRequest;
    }
}
