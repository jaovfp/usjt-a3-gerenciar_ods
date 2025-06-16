package usjt.atividade.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

    private String eventId;
    private ODS ods;
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private String addressLine;
    private String city;
    private String state;
    private String postalCode;
    private User createdBy;
    private LocalDateTime createDate;

    public Event() {
        this.eventId = UUID.randomUUID().toString();
        this.createDate = LocalDateTime.now();
    }

    public Event(ODS odsId, String eventName, String eventDescription,
                 LocalDate eventDate, String addressLine, String city,
                 String state, String postalCode, User createdBy) {
        this();
        this.ods = ods;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.createdBy = createdBy;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public ODS getOds() {
        return ods;
    }

    public void setOds(ODS odsId) {
        this.ods = odsId;
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
