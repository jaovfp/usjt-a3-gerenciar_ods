package usjt.atividade.infra.controller;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.app.Events.EventServiceImpl;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.Event;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class EventController {

    private final EventServiceImpl eventService;

    public EventController(){
        this.eventService = new EventServiceImpl();
    }

    public Response<PaginatedResponse<Event>> getPaginatedEvents(int page, int pageSize, EventFilter filter){
        try{
            PaginatedResponse<Event> eventSubscribes = eventService.getPaginatedEvents(page, pageSize, filter);
            return Response.ok(eventSubscribes);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }
}
