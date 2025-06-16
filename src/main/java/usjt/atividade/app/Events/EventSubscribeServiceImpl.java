package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.EventSubscribeDto;
import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.EventSubscribe;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.service.EventSubscribeService;
import usjt.atividade.infra.Repository.EventRepositoryImpl;
import usjt.atividade.infra.Repository.EventSubscribeRepositoryImpl;
import usjt.atividade.infra.Repository.UserRepositoryImpl;

import java.util.List;
import java.util.UUID;

import static usjt.atividade.app.Events.Validator.EventValidator.*;

public class EventSubscribeServiceImpl implements EventSubscribeService {

    private final EventSubscribeRepositoryImpl repository;
    private final UserRepositoryImpl userRepository;
    private EventRepositoryImpl eventRepository;

    public EventSubscribeServiceImpl() {
        this.repository = new EventSubscribeRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
        this.eventRepository = new EventRepositoryImpl();
    }

    @Override
    public PaginatedResponse<EventSubscribe> getPaginatedEventSubscribes(int page, int size, EventSubscribeFilter filter) {
        int totalItems = repository.countEventSubscribesByFilter(filter);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_SUBSCRIBES_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<EventSubscribe> items = repository.findAllEventSubscribesByFilter(offset, size, filter);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }

    @Override
    public void subscribe(EventSubscribeDto request) {

        validateEventSubscribeRequest(request);

        User user = getUserOrThrow(request.getUserId());
        Event event = getEventOrThrow(request.getEventId());


        validateUserHasCompleteProfile(user);
        validateEventDateBeforeToday(event.getEventDate());
        validateAlreadySubscribed(request.getEventId(), request.getUserId());
        validateUserIsNotEventCreator(user, event);

        EventSubscribe subscribe = new EventSubscribe(event, user);
        repository.save(subscribe);
    }

    @Override
    public void unsubscribe(EventSubscribeDto request) {
        validateEventSubscribeRequest(request);

        User user = getUserOrThrow(request.getUserId());
        Event event = getEventOrThrow(request.getEventId());

        validateSubscriptionExists(request.getEventId(), request.getUserId());

        repository.deleteByEventIdAndUserId(event.getEventId(), user.getUserId());
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.findById(userId.toString())
                .orElseThrow(() -> new UnprocessableEntityException(MessageConstants.USER_NOT_FOUND));
    }

    private Event getEventOrThrow(UUID eventId) {
        return eventRepository.findById(eventId.toString())
                .orElseThrow(() -> new UnprocessableEntityException(MessageConstants.EVENT_NOT_FOUND));
    }

    private void validateUserIsNotEventCreator(User user, Event event) {
        if (event.getCreatedBy() != null && user.getUserId().equals(event.getCreatedBy().getUserId())) {
            throw new UnprocessableEntityException(MessageConstants.EVENT_CREATOR_CANNOT_SUBSCRIBE);
        }
    }

    public void validateAlreadySubscribed(UUID eventId, UUID userId) {
        if (repository.existsByEventIdAndUserId(eventId, userId)) {
            throw new UnprocessableEntityException(MessageConstants.ALREADY_SUBSCRIBED);
        }
    }

    private void validateSubscriptionExists(UUID eventId, UUID userId) {
        if (!repository.existsByEventIdAndUserId(eventId, userId)) {
            throw new UnprocessableEntityException(MessageConstants.SUBSCRIBE_ALREADY_DELETED);
        }
    }
}

