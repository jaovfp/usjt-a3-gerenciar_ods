package usjt.atividade.domain.repository;

import usjt.atividade.domain.entities.PasswordRecovery;

import java.util.Optional;
import java.util.UUID;

public interface PassowordRecoveryRepository {
    void save(PasswordRecovery passwordRecovery);
    Optional<PasswordRecovery> findValidById(UUID recoveryId);
    void invalidatePin(UUID recoveryId);
}
