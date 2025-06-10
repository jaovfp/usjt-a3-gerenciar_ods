package usjt.atividade.app.Events.DTO;

import usjt.atividade.domain.valueObjects.EventRequestStatus;

public class MyEventRequestFilter {

    private String eventName;
    private EventRequestStatus status;

    public MyEventRequestFilter(String eventName, EventRequestStatus status){
        this.eventName = eventName;
        this.status = status;
    }

    public String getEventName() {
        return eventName;
    }

    public EventRequestStatus getStatus() {
        return status;
    }
}
