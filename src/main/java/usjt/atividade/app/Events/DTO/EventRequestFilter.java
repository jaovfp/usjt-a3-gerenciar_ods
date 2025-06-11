package usjt.atividade.app.Events.DTO;

import usjt.atividade.domain.valueObjects.EventRequestStatus;

public class EventRequestFilter {

    private String userId;
    private String requestId;
    private String eventName;
    private EventRequestStatus status;

    public EventRequestFilter(String eventName, EventRequestStatus status, String userId, String requestId){
        this.eventName = eventName;
        this.status = status;
        this.userId = userId;
        this.requestId = requestId;
    }

    public String getEventName() {
        return eventName;
    }

    public EventRequestStatus getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getRequestId() {
        return requestId;
    }
}
