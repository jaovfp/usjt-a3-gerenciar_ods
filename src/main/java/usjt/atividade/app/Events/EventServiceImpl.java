package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.MyEventRequestFilter;
import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.infra.Repository.EventRepository;

import java.util.List;
import java.util.UUID;

public class EventServiceImpl {

    private final EventRepository repository;

    public EventServiceImpl() {
        this.repository = new EventRepository();
    }

    public PaginatedResponse<MyEventsRequest> getPaginatedEventRequests(UUID userId, int page, int size, MyEventRequestFilter filter) {
        int totalItems = repository.countByUserIdAndFilter(userId.toString(), filter);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<MyEventsRequest> items = repository.findAllEventRequestsByUserIdAndFilter(userId.toString(), offset, size, filter);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }
}
