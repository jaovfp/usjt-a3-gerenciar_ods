package usjt.atividade.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventSubscribe {

    private String subscribeId;

    private Event event;

    private User user;

    private LocalDateTime registrationDate;

    public EventSubscribe() {
        this.subscribeId = UUID.randomUUID().toString();
        this.registrationDate = LocalDateTime.now();
    }

    public EventSubscribe(Event event, User user) {
        this();
        this.event = event;
        this.user = user;
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

}
