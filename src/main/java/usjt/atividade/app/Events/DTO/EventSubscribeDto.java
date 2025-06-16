package usjt.atividade.app.Events.DTO;

import java.util.UUID;

public class EventSubscribeDto {

    private UUID eventId;
    private UUID userId;

    public EventSubscribeDto() {
    }

    public EventSubscribeDto(UUID eventId, UUID userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
