package usjt.atividade.app.Events.DTO;

public class EventSubscribeFilter {

    private String userId;
    private String subscribeId;
    private String eventName;
    private String odsId;

    public EventSubscribeFilter(String eventName, String odsId, String userId, String subscribeId){
        this.eventName = eventName;
        this.odsId = odsId;
        this.userId = userId;
        this.subscribeId = subscribeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getOdsId() {
        return odsId;
    }
}
