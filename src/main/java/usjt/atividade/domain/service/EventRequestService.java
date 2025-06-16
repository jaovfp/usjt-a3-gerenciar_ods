package usjt.atividade.domain.service;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.EventRequest;

public interface EventRequestService {

    PaginatedResponse<EventRequest> getPaginatedEventRequests(int page, int size, EventRequestFilter filter);
    void create(CreateEventRequestDto request);
    EventRequest updateStatus(UpdateEventRequestStatusDto dto);
}
