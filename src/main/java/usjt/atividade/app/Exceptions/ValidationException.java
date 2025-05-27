package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public class ValidationException  extends ErrorException {

    private static final StatusCode STATUS_CODE = StatusCode.BAD_REQUEST;

    public ValidationException(String message) {
        super(message, STATUS_CODE);
    }

}
