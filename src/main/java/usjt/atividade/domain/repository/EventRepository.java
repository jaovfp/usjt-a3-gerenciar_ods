package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.domain.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    int countEventsByFilter(EventFilter filter);
    List<Event> findAllEventsByFilter(int offset, int pageSize, EventFilter filter);
    void save(Event event);
    Optional<Event> findById(String eventId);

}
