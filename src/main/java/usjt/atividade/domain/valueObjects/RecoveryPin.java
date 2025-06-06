package usjt.atividade.domain.valueObjects;

import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.common.MessageConstants;

public class RecoveryPin {
    private final String value;

    public RecoveryPin(String value) {
        if (!value.matches("\\d{6}")) {
            throw new UnprocessableEntityException(MessageConstants.PIN_CODE_ERROR_LENGHT);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
