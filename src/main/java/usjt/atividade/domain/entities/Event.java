package usjt.atividade.domain.entities;

import usjt.atividade.domain.valueObjects.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

    private UUID eventId;
    private ODS ods;
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private Address address;
    private User createdBy;
    private LocalDateTime createDate;
    private int totalRegistrations;

    public Event() {
        this.eventId = UUID.randomUUID();
        this.createDate = LocalDateTime.now();
    }

    public Event(ODS ods, String eventName, String eventDescription,
                 LocalDate eventDate, Address address, User createdBy) {
        this();
        this.ods = ods;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.address = address;
        this.createdBy = createdBy;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
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

    public Address getAddress() {
        return address;
    }

    public int getTotalRegistrations() {
        return totalRegistrations;
    }
    public void setTotalRegistrations(int totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
