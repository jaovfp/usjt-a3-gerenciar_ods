package usjt.atividade.common;

import usjt.atividade.domain.model.User.User;

public class Response <T>{

    private final boolean success;
    private final StatusCode statusCode;
    private final String message;
    private final T data;

    private Response(boolean success, StatusCode statusCode, String message, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(true, StatusCode.SUCCESS, null, data);
    }

    public static <T> Response<T> created(String message) {
        return new Response<>(true, StatusCode.CREATED, message, null);
    }

    public static <T> Response<T> fail(StatusCode code, String message) {
        return new Response<>(false, code, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
