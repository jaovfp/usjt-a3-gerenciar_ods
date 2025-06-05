package usjt.atividade.domain.entities;

import usjt.atividade.domain.valueObjects.EventRequestStatus;

import javax.mail.Address;
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

    private Address address;

    private EventRequestStatus status;
    private LocalDateTime createDate;

    public EventRequest() {}

    public EventRequest(UUID requestId, UUID userId, UUID odsId, String eventName,
                        String eventDescription, LocalDate eventDate, Address address, EventRequestStatus status,
                        LocalDateTime createDate) {
        this.requestId = requestId;
        this.userId = userId;
        this.odsId = odsId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.address = address;
        this.status = status;
        this.createDate = createDate;
    }
}
