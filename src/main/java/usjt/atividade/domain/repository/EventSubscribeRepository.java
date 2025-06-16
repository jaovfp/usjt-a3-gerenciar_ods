package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.domain.entities.EventSubscribe;

import java.util.List;
import java.util.UUID;

public interface EventSubscribeRepository {

    List<EventSubscribe> findAllEventSubscribesByFilter(int offset, int pageSize, EventSubscribeFilter filter);
    int countEventSubscribesByFilter(EventSubscribeFilter filter);
    boolean existsByEventIdAndUserId(UUID eventId, UUID userId);
    void save(EventSubscribe eventSubscribe);
    void deleteByEventIdAndUserId(UUID eventId, UUID userId);

}
