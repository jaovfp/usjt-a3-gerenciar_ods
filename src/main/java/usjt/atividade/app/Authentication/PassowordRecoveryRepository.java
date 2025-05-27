package usjt.atividade.app.Authentication;

import usjt.atividade.domain.model.PasswordRecovery;

public interface PassowordRecoveryRepository {
    void save(PasswordRecovery passwordRecovery);
}
