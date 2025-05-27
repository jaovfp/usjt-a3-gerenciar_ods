package usjt.atividade.app.User;

import usjt.atividade.app.User.dto.requests.CreateUserRequest;

public interface UserService {
    void create(CreateUserRequest request);
}
