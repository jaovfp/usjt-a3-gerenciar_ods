package usjt.atividade.app.Events.DTO;



import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.UserType;

import java.util.UUID;

public class UpdateEventRequestStatusDto {

    private UUID requestId;
    private UUID actorUserId;
    private UserType userType;
    private String newStatus;

    public UpdateEventRequestStatusDto(UUID requestId, UUID actorUserId, UserType userType, String newStatus){
        this.newStatus = newStatus;
        this.actorUserId = actorUserId;
        this.requestId = requestId;
        this.userType = userType;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public UUID getActorUserId() {
        return actorUserId;
    }

    public void setActorUserId(UUID actorUserId) {
        this.actorUserId = actorUserId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public UserType getUserType() {
        return userType;
    }
}
