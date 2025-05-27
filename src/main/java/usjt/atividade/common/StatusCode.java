package usjt.atividade.common;

public enum StatusCode {
    SUCCESS(200, "SUCCESS"),
    CREATED(201, "CREATED"),
    NOT_FOUND(404, "NOT_FOUND"),
    UNPROCESSABLE_ENTITY_ERROR(422, "VALIDATION_ERROR"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR"),
    BAD_REQUEST(400, "BAD_REQUEST");

    private final int code;
    private final String status;

    StatusCode(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
