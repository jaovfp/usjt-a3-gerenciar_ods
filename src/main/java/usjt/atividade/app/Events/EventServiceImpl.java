package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.service.EventService;
import usjt.atividade.infra.Repository.EventRepositoryImpl;

import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventRepositoryImpl repository;

    public EventServiceImpl() {
        this.repository = new EventRepositoryImpl();
    }

    public PaginatedResponse<Event> getPaginatedEvents(int page, int size, EventFilter filter) {
        int totalItems = repository.countEventsByFilter(filter);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_SUBSCRIBES_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<Event> items = repository.findAllEventsByFilter(offset, size, filter);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }

}
