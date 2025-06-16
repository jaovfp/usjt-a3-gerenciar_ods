package usjt.atividade.domain.valueObjects;

public enum EventRequestStatus {
    PENDING("Pendente"),
    APPROVED("Aprovado"),
    REJECTED("Rejeitado"),
    CANCELED("Cancelado");

    private final String status;

    EventRequestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
