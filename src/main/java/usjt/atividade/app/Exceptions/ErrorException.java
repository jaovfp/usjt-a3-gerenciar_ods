package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public abstract class ErrorException extends RuntimeException {
    private final StatusCode statusCode;

    public ErrorException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode(){
        return this.statusCode;
    }
}
