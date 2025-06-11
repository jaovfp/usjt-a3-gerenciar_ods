package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.MyEventsRequest;

import java.util.List;
import java.util.UUID;

public interface EventRepository {
    List<MyEventsRequest> findAllEventRequestsByUserId(String userId, int offset, int pageSize);
    int countByUserId(UUID userId);
    List<MyEventsRequest> findAllEventsByStatus (int offset, int pageSize, String status);
    List<MyEventsRequest> findAllEvents (int offset, int pageSize);
}
