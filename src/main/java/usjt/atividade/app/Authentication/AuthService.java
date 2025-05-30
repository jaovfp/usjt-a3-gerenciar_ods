package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.domain.model.User.User;

public interface AuthService {
    User authenticate(AuthenticateRequest request);
}
