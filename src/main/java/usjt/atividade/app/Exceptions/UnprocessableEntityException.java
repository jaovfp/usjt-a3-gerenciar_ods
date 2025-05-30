package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public class UnprocessableEntityException extends ErrorException{
    private static final StatusCode STATUS_CODE = StatusCode.UNPROCESSABLE_ENTITY_ERROR;

    public UnprocessableEntityException(String message) {
        super(message, STATUS_CODE);
    }
}
