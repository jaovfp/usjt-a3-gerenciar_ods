package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.domain.entities.EventSubscribe;

import java.util.List;

public interface EventSubscribeRepository {

    List<EventSubscribe> findAllEventSubscribesByFilter(int offset, int pageSize, EventSubscribeFilter filter);
    int countEventSubscribesByFilter(EventSubscribeFilter filter);

}
