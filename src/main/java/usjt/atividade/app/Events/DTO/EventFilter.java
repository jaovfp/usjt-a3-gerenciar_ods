package usjt.atividade.app.Events.DTO;

public class EventFilter {

    private String creatorId;
    private String eventId;
    private String eventName;
    private String odsId;
    private Boolean beforeToday;


    public EventFilter(String eventName, String odsId, String creatorId, String eventId, boolean beforeToday){
        this.eventName = eventName;
        this.odsId = odsId;
        this.creatorId = creatorId;
        this.eventId = eventId;
        this.beforeToday = beforeToday;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getSubscribeId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getOdsId() {
        return odsId;
    }

    public Boolean getBeforeToday() {
        return beforeToday;
    }
}
