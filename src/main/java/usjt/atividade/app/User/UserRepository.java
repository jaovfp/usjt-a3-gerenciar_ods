package usjt.atividade.app.User;

import usjt.atividade.domain.model.User.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    boolean existsByEmail(String email);
    void save(User user);
    Optional<User> findByEmail(String email);
    void update(User user);
    Optional<User> findById(String userId);
}
