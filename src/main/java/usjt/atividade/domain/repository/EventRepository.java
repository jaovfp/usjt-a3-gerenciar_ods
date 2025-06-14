package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.domain.entities.Event;

import java.util.List;

public interface EventRepository {

    int countEventsByFilter(EventFilter filter);
    List<Event> findAllEventsByFilter(int offset, int pageSize, EventFilter filter);

}
