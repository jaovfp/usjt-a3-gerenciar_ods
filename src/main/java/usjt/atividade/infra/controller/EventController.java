package usjt.atividade.infra.controller;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventsRequest;
import usjt.atividade.app.Events.EventServiceImpl;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.repository.OdsRepository;
import usjt.atividade.infra.Repository.OdsRepositoryImpl;

import java.util.List;
import java.util.UUID;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class EventController {

    private final EventServiceImpl eventServiceImpl;

    public EventController(){
        this.eventServiceImpl = new EventServiceImpl();
    }

    public Response<PaginatedResponse<EventsRequest>> getEventRequests(int page, int pageSize, EventRequestFilter filter){
        try{
            PaginatedResponse<EventsRequest> eventsRequests = eventServiceImpl.getPaginatedEventRequests(page, pageSize, filter);
            return Response.ok(eventsRequests);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<List<ODS>> getOdsTopics (){
        try{
            List<ODS> odsTopics = eventServiceImpl.getOdsTopics();
            return Response.ok(odsTopics);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }
}
