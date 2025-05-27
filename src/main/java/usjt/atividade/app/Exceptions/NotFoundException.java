package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public class NotFoundException extends ErrorException{

    private static final StatusCode STATUS_CODE = StatusCode.NOT_FOUND;

    public NotFoundException(String message) {
        super(message, STATUS_CODE);
    }
}
