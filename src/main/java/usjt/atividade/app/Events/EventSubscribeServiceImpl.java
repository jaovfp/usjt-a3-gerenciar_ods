package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.EventSubscribe;
import usjt.atividade.domain.service.EventSubscribeService;
import usjt.atividade.infra.Repository.EventSubscribeRepositoryImpl;

import java.util.List;

public class EventSubscribeServiceImpl implements EventSubscribeService {

    private final EventSubscribeRepositoryImpl repository;

    public EventSubscribeServiceImpl() {
        this.repository = new EventSubscribeRepositoryImpl();
    }

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
}
