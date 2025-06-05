package usjt.atividade.domain.service;

import usjt.atividade.app.User.dto.requests.CreateUserRequest;

public interface UserService {
    void create(CreateUserRequest request);
}
