package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventsRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.infra.Repository.EventRequestRepository;
import usjt.atividade.infra.Repository.OdsRepositoryImpl;

import java.util.List;

public class EventServiceImpl {

    private final EventRequestRepository repository;
    private final OdsRepositoryImpl odsRepository;

    public EventServiceImpl() {
        this.repository = new EventRequestRepository();
        this.odsRepository = new OdsRepositoryImpl();
    }

    public PaginatedResponse<EventsRequest> getPaginatedEventRequests(int page, int size, EventRequestFilter filter) {
        int totalItems = repository.countEventRequestByFilter(filter);
        if (totalItems == 0){
            throw new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND);
        }
        int totalPages = (int) Math.ceil((double) totalItems / size);
        int offset = (page - 1) * size;

        List<EventsRequest> items = repository.findAllEventRequestsByFilter(offset, size, filter);

        return new PaginatedResponse<>(items, page, totalPages, totalItems);
    }

    public List<ODS> getOdsTopics(){
        List<ODS> odsList = odsRepository.getAllOds();
        if (odsList.isEmpty()) {
            throw new NotFoundException(MessageConstants.ODS_TOPICS_NOT_FOUND);
        }
        return odsList;
    }
}
