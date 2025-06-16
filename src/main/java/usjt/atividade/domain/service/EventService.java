package usjt.atividade.domain.service;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.EventRequest;

public interface EventService {

    PaginatedResponse<Event> getPaginatedEvents(int page, int size, EventFilter filter);
    void createEventFromRequest(EventRequest eventRequest);

}
