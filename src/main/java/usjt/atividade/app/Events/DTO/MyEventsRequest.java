package usjt.atividade.app.Events.DTO;

import java.time.LocalDateTime;

public class MyEventsRequest {

    private String requestId;
    private String eventName;
    private String eventDescription;
    private String status;
    private String odsName;
    private LocalDateTime createDate;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOdsName() {
        return odsName;
    }

    public void setOdsName(String odsName) {
        this.odsName = odsName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
