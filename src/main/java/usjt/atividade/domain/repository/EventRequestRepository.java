package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRequestRepository {

    int countEventRequestByFilter(EventRequestFilter filter);
    List<EventRequest> findAllEventRequestsByFilter(int offset, int pageSize, EventRequestFilter filter);
    void save(EventRequest request);
    boolean existsPendingOrApprovedByUserIdAndEventDate(UUID userId, LocalDate eventDate);
    void update(EventRequest request);
    Optional<EventRequest> findById(String requestId);
}
