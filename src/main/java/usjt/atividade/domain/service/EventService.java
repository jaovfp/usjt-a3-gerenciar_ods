package usjt.atividade.domain.service;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.Event;

public interface EventService {

    PaginatedResponse<Event> getPaginatedEvents(int page, int size, EventFilter filter);

}
