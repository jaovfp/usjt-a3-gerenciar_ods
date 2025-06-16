package usjt.atividade.domain.service;

import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;
import usjt.atividade.domain.entities.User;

public interface UserService {
    void create(CreateUserRequest request);
    User update(UpdateUserRequest request);
}
