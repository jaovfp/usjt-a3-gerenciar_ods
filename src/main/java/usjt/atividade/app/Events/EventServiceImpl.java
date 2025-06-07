package usjt.atividade.app.Events;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.infra.Repository.EventRepository;

import java.util.List;
import java.util.UUID;

public class EventServiceImpl {

    private final EventRepository repository;

    public EventServiceImpl() {
        this.repository = new EventRepository();
    }

    public List<MyEventsRequest> getPaginatedEventRequests(UUID userId, int page, int size) {
        int offset = (page - 1) * size;
        List<MyEventsRequest> myEventsRequestList = repository.findAllEventRequestsByUserId(userId.toString(), offset, size);
        if (myEventsRequestList.isEmpty()){
            throw new NotFoundException(MessageConstants.EVENTS_REQUESTS_NOT_FOUND);
        }
        return myEventsRequestList;
    }
}
