package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.service.EventRequestService;
import usjt.atividade.infra.Repository.EventRequestRepositoryImpl;

import java.util.List;

public class EventRequestServiceImpl implements EventRequestService {

    private final EventRequestRepositoryImpl repository;

    public EventRequestServiceImpl() {
        this.repository = new EventRequestRepositoryImpl();
    }

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
}
