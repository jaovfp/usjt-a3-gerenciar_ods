package usjt.atividade.domain.entities;

import usjt.atividade.domain.valueObjects.Address;
import usjt.atividade.domain.valueObjects.EventRequestStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventsRequest {

    private UUID requestId;
    private UUID userId;
    private ODS ods;
    private String eventName;
    private String eventDescription;
    private EventRequestStatus status;
    private LocalDateTime createDate;
    private LocalDate eventDate;
    private Address address;


    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
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

    public EventRequestStatus getStatus() {
        return status;
    }

    public void setStatus(EventRequestStatus status) {
        this.status = status;
    }

    public ODS getOds() {
        return ods;
    }

    public void setOds(ODS ods) {
        this.ods = ods;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Address getAddress() {
        return address;
    }
}
