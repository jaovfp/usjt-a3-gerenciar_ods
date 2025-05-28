package usjt.atividade.app.Authentication.dto;

import java.util.UUID;

public class ResetPasswordRequest {

    String pinCode;
    String password;
    UUID recoveryId;

    public ResetPasswordRequest(String pinCode, String password, UUID recoveryId){
        this.pinCode = pinCode;
        this.password = password;
        this.recoveryId = recoveryId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPassword() {
        return password;
    }

    public UUID getRecoveryId() {
        return recoveryId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
