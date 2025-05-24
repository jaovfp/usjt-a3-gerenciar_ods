package usjt.atividade.domain.model;

import java.time.LocalDateTime;

public class PasswordRecovery {

    private int recoveryId;
    private int userId;
    private String pin;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;

    public PasswordRecovery(int recoveryId, int userId, String pin,
                            LocalDateTime createdAt, LocalDateTime expiresAt, boolean isUsed) {
        this.recoveryId = recoveryId;
        this.userId = userId;
        this.pin = pin;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isUsed = isUsed;
    }

    public int getRecoveryId() {
        return recoveryId;
    }

    public void setRecoveryId(int recoveryId) {
        this.recoveryId = recoveryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
