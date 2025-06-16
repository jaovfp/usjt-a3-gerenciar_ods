package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.Event;

import java.util.List;
import java.util.UUID;

public interface EventRepository {
    int countEventsByFilter(EventFilter filter);// foi
    List<Event> findAllEventsByFilter(int offset, int pageSize, EventFilter filter); // foi

    List<EventRequestFilter> findEventsByUserName(String name, int offset, int pageSize, EventFilter filter);
}
