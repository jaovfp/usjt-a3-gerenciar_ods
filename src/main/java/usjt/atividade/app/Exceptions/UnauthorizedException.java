package usjt.atividade.app.Exceptions;

import usjt.atividade.common.StatusCode;

public class UnauthorizedException extends ErrorException{

    private static final StatusCode STATUS_CODE = StatusCode.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message, STATUS_CODE);
    }

}
