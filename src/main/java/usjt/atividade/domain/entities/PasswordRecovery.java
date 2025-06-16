package usjt.atividade.domain.entities;

import usjt.atividade.domain.valueObjects.RecoveryPin;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordRecovery {

    private UUID recoveryId;
    private UUID userId;
    private RecoveryPin pin;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;

    public PasswordRecovery(UUID recoveryId, UUID userId, RecoveryPin pin,
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

    public RecoveryPin getPin() {
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
