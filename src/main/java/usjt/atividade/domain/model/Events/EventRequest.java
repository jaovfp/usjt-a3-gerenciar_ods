package usjt.atividade.domain.model.Events;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventRequest {

    private UUID requestId;
    private UUID userId;
    private UUID odsId;

    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;

    private String addressLine;
    private String city;
    private String state;
    private String postalCode;

    private Status status;
    private LocalDateTime createDate;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELED
    }

    public EventRequest() {}

    public EventRequest(UUID requestId, UUID userId, UUID odsId, String eventName,
                        String eventDescription, LocalDate eventDate, String addressLine,
                        String city, String state, String postalCode, Status status,
                        LocalDateTime createDate) {
        this.requestId = requestId;
        this.userId = userId;
        this.odsId = odsId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.status = status;
        this.createDate = createDate;
    }
}
