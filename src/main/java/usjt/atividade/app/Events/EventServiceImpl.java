package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.infra.Repository.EventRepositoryimpl;

import java.util.List;
import java.util.UUID;

public class EventServiceImpl {

    private final EventRepositoryimpl repository;

    public EventServiceImpl() {
        this.repository = new EventRepositoryimpl();
    }

    public PaginatedResponse<MyEventsRequest> getPaginatedEventRequests(UUID userId, int page, int size) {
        int totalItems = repository.countByUserId(userId);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<MyEventsRequest> items = repository.findAllEventRequestsByUserId(userId.toString(), offset, size);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }
}
