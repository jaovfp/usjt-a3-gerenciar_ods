package usjt.atividade.domain.service;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.domain.entities.User;

public interface AuthService {
    User authenticate(AuthenticateRequest request);
}
