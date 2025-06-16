package usjt.atividade.infra.controller;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.app.Events.EventRequestServiceImpl;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class EventRequestController {

    private final EventRequestServiceImpl eventRequestServiceImpl;

    public EventRequestController(){
        this.eventRequestServiceImpl = new EventRequestServiceImpl();
    }

    public Response<PaginatedResponse<EventRequest>> getEventRequests(int page, int pageSize, EventRequestFilter filter){
        try{
            PaginatedResponse<EventRequest> eventsRequests = eventRequestServiceImpl.getPaginatedEventRequests(page, pageSize, filter);
            return Response.ok(eventsRequests);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> createEventRequest(CreateEventRequestDto request){
        try{
            eventRequestServiceImpl.create(request);
            return Response.created(MessageConstants.CREATE_EVENT_REQUEST_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }
}
