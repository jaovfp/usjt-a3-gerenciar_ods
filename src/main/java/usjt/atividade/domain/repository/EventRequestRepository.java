package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;

import java.util.List;

public interface EventRequestRepository {

    int countEventRequestByFilter(EventRequestFilter filter);
    List<EventRequest> findAllEventRequestsByFilter(int offset, int pageSize, EventRequestFilter filter);

}
