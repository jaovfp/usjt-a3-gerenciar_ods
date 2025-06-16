package usjt.atividade.app.Events.DTO;

public class EventFilter {

    private String creatorId;
    private String eventId;
    private String eventName;
    private String odsId;
    private Boolean afterToday;

    public EventFilter(){

    }

    public EventFilter(String eventName, String odsId, String creatorId, String eventId, boolean afterToday){
        this.eventName = eventName;
        this.odsId = odsId;
        this.creatorId = creatorId;
        this.eventId = eventId;
        this.afterToday = afterToday;
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

    public Boolean getAfterToday() {
        return afterToday;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
}
