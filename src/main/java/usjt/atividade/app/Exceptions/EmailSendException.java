package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public class EmailSendException extends ErrorException{

    private static final StatusCode STATUS_CODE = StatusCode.INTERNAL_ERROR;

    public EmailSendException(String message) {
        super(message, STATUS_CODE);
    }

}
