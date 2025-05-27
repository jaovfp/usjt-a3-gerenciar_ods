package usjt.atividade.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordRecovery {

    private UUID recoveryId;
    private UUID userId;
    private String pin;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;

    public PasswordRecovery(UUID recoveryId, UUID userId, String pin,
                            LocalDateTime createdAt, LocalDateTime expiresAt, boolean isUsed) {
        this.recoveryId = recoveryId;
        this.userId = userId;
        this.pin = pin;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isUsed = isUsed;
    }

    public UUID getRecoveryId() {
        return recoveryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
