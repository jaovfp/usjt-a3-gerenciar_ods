package usjt.atividade.domain.service;

import usjt.atividade.app.Events.DTO.EventSubscribeDto;
import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.entities.EventSubscribe;

public interface EventSubscribeService {

    PaginatedResponse<EventSubscribe> getPaginatedEventSubscribes(int page, int size, EventSubscribeFilter filter);
    void subscribe(EventSubscribeDto request);
    void unsubscribe(EventSubscribeDto request);
}
