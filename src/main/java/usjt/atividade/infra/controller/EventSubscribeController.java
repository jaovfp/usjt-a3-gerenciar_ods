package usjt.atividade.infra.controller;

import usjt.atividade.app.Events.DTO.EventSubscribeDto;
import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.app.Events.EventSubscribeServiceImpl;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.EventSubscribe;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class EventSubscribeController {

    private final EventSubscribeServiceImpl eventSubscribeService;

    public EventSubscribeController(){
        this.eventSubscribeService = new EventSubscribeServiceImpl();
    }

    public Response<PaginatedResponse<EventSubscribe>> getPaginatedEventSubscribes(int page, int pageSize, EventSubscribeFilter filter){
        try{
            PaginatedResponse<EventSubscribe> eventSubscribes = eventSubscribeService.getPaginatedEventSubscribes(page, pageSize, filter);
            return Response.ok(eventSubscribes);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> subscribe(EventSubscribeDto request){
        try{
            eventSubscribeService.subscribe(request);
            return Response.ok();
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> unsubscribe(EventSubscribeDto request){
        try{
            eventSubscribeService.unsubscribe(request);
            return Response.ok();
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
