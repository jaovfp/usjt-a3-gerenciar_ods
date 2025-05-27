package usjt.atividade.app.User;

import usjt.atividade.domain.model.User.User;

public interface UserRepository {
    boolean existsByEmail(String email);
    void save(User user);
    User findByEmail(String email);
}
